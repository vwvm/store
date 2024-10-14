import PyPDF2


def split_pdf(input_pdf, output_folder):
    # �� PDF �ļ�
    with open(input_pdf, "rb") as pdf_file:
        reader = PyPDF2.PdfFileReader(pdf_file)

        # ȷ�� PDF �Ǽ��ܵģ������Խ���
        if reader.isEncrypted:
            reader.decrypt('')

        # ����ÿһҳ�������䱣��Ϊ������ PDF �ļ�
        for page_num in range(reader.numPages):
            writer = PyPDF2.PdfFileWriter()
            writer.addPage(reader.getPage(page_num))

            output_filename = f"{output_folder}/page_{page_num + 1}.pdf"
            with open(output_filename, "wb") as output_pdf:
                writer.write(output_pdf)

            print(f"Saved: {output_filename}")


# ʹ�ú������ PDF
input_pdf_path = "input.pdf"  # �滻Ϊ������� PDF �ļ�·��
output_folder_path = "output_folder"  # �滻Ϊ������Ŀ¼
split_pdf(input_pdf_path, output_folder_path)
