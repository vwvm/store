import PyPDF2


def split_pdf(input_pdf, output_folder):
    # 打开 PDF 文件
    with open(input_pdf, "rb") as pdf_file:
        reader = PyPDF2.PdfFileReader(pdf_file)

        # 确保 PDF 是加密的，并尝试解密
        if reader.isEncrypted:
            reader.decrypt('')

        # 遍历每一页，并将其保存为单独的 PDF 文件
        for page_num in range(reader.numPages):
            writer = PyPDF2.PdfFileWriter()
            writer.addPage(reader.getPage(page_num))

            output_filename = f"{output_folder}/page_{page_num + 1}.pdf"
            with open(output_filename, "wb") as output_pdf:
                writer.write(output_pdf)

            print(f"Saved: {output_filename}")


# 使用函数拆分 PDF
input_pdf_path = "input.pdf"  # 替换为你的输入 PDF 文件路径
output_folder_path = "output_folder"  # 替换为你的输出目录
split_pdf(input_pdf_path, output_folder_path)
