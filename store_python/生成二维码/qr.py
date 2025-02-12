import qrcode

# 生成二维码
data = "http://192.168.110.32:3502/test"

qr = qrcode.QRCode(
    version=1,  # 控制二维码的大小，范围1-40，数字越大二维码越大
    error_correction=qrcode.constants.ERROR_CORRECT_L,  # 纠错等级L6% M15% Q25% H30%
    box_size=10,  # 每个小格子的像素大小
    border=4,  # 边框厚度
)
qr.add_data(data)
qr.make(fit=True)

# 生成图片
img = qr.make_image(fill="black", back_color="white")
img.show()  # 显示二维码
img.save("qrcode.png")  # 保存为图片
