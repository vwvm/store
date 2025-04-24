import turtle
import random

# 设置画布和画笔
screen = turtle.Screen()
screen.setup(width=800, height=600)
screen.bgcolor("black")
screen.title("Fireworks Show")
pen = turtle.Turtle()
pen.speed(0)
pen.hideturtle()
pen.penup()

# 定义烟花函数
def firework(x, y):
    # 随机选择烟花颜色
    colors = ["red", "orange", "yellow", "green", "blue", "purple", "pink"]
    color = random.choice(colors)
    pen.goto(x, y)
    pen.pendown()
    pen.color(color)
    # 随机生成烟花的线条数量
    num_lines = random.randint(10, 30)
    # 绘制烟花
    for _ in range(num_lines):
        pen.forward(random.randint(50, 150))
        pen.backward(random.randint(50, 150))
        pen.right(360 / num_lines)
    pen.penup()

# 主循环，随机生成烟花位置
for _ in range(10):
    x = random.randint(-350, 350)
    y = random.randint(-250, 250)
    firework(x, y)

# 保持窗口打开
turtle.done()