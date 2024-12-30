from PySide6.QtWidgets import QApplication, QFileDialog
from PySide6.QtCore import QObject, Signal, Slot
from PySide6.QtQml import QQmlApplicationEngine
import PyPDF2
import sys
import os


class Backend(QObject):
    def __init__(self, engine):
        super().__init__()
        self.engine = engine
        self.file_path = ""

    @Slot()
    def open_file_dialog(self):
        # 使用文件对话框让用户选择文件
        self.file_path, _ = QFileDialog.getOpenFileName(None, "选择一个PDF文件", "", "PDF Files (*.pdf)")
        if self.file_path:
            # 提取文件名
            file_name = os.path.basename(self.file_path)

            # 直接更新 QML 中的输入框内容
            root_object = self.engine.rootObjects()[0]  # 获取 QML 的根对象

            # 更新文件名输入框
            file_input = root_object.findChild(QObject, "filePathInput1")
            if file_input:
                file_input.setProperty("text", file_name)

            # 更新页数信息
            page_input = root_object.findChild(QObject, "page_long")
            if page_input:
                page_count = self.get_pdf_page_count(self.file_path)
                page_input.setProperty("text", f"共 {page_count} 页")

    @Slot()
    def split_pdf(self):
        # 获取 QML 中的拆分页码
        root_object = self.engine.rootObjects()[0]
        input_page_obj = root_object.findChild(QObject, "input_page")
        if input_page_obj:
            split_page = input_page_obj.property("text")
            try:
                split_page = int(split_page)
            except ValueError:
                print("输入页码无效")
                return

            # 获取总页数
            page_count = self.get_pdf_page_count(self.file_path)
            if split_page <= 0 or split_page >= page_count:
                print("拆分页码超出范围")
                return

            # 拆分 PDF
            try:
                with open(self.file_path, "rb") as pdf_file:
                    reader = PyPDF2.PdfReader(pdf_file)
                    writer1 = PyPDF2.PdfWriter()
                    writer2 = PyPDF2.PdfWriter()

                    # 第一部分 PDF
                    for i in range(split_page):
                        writer1.add_page(reader.pages[i])
                    base_name = os.path.splitext(os.path.basename(self.file_path))[0]
                    dir_name = os.path.dirname(self.file_path)
                    output_path1 = os.path.join(dir_name, f"{base_name}-p1-{split_page}.pdf")
                    with open(output_path1, "wb") as out_file1:
                        writer1.write(out_file1)

                    # 第二部分 PDF
                    for i in range(split_page, page_count):
                        writer2.add_page(reader.pages[i])
                    output_path2 = os.path.join(dir_name, f"{base_name}-p{split_page + 1}-{page_count}.pdf")
                    with open(output_path2, "wb") as out_file2:
                        writer2.write(out_file2)

                    # 更新 QML 中的输出路径
                    pdf_out_path1 = root_object.findChild(QObject, "pdf_out_path1")
                    pdf_out_path2 = root_object.findChild(QObject, "pdf_out_path2")
                    if pdf_out_path1:
                        pdf_out_path1.setProperty("text", output_path1)
                    if pdf_out_path2:
                        pdf_out_path2.setProperty("text", output_path2)

            except Exception as e:
                print(f"无法拆分 PDF 文件: {e}")

    def get_pdf_page_count(self, pdf_path):
        try:
            with open(pdf_path, 'rb') as pdf_file:
                reader = PyPDF2.PdfReader(pdf_file)
                return len(reader.pages)
        except Exception as e:
            print(f"无法读取 PDF 页数: {e}")
            return 0


app = QApplication(sys.argv)
engine = QQmlApplicationEngine()

# 创建 Backend 实例，并传递 engine
backend = Backend(engine)
engine.rootContext().setContextProperty("backend", backend)

# 加载 QML 文件
qml_file_path = "view.qml"  # 确保 view.qml 文件在同一目录下
engine.load(qml_file_path)

if not engine.rootObjects():
    sys.exit(-1)

sys.exit(app.exec())