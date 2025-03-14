import turtle
import random

# 设置画布和画笔
screen = turtle.Screen()
screen.setup(width=800, height=600)
screen.bgcolor("black")
screen.title("Fireworks Show")
screen.tracer(0)

# 创建烟花函数
def create_firework():
    # 创建一个画笔对象
    firework = turtle.Turtle()
    firework.speed(0)
    firework.color(random.choice(["red", "orange", "yellow", "green", "blue", "purple"]))
    firework.penup()
    # 随机选择发射位置
    x = random.randint(-380, 380)
    y = -280
    firework.goto(x, y)
    firework.pendown()
    return firework

# 发射烟花函数
def launch_firework(firework):
    # 烟花上升
    while firework.ycor() < 250:
        firework.sety(firework.ycor() + 5)
        screen.update()
    # 烟花绽放
    colors = ["red", "orange", "yellow", "green", "blue", "purple"]
    for _ in range(30):
        length = random.randint(10, 50)
        angle = random.randint(0, 360)
        firework.color(random.choice(colors))
        firework.pensize(random.randint(1, 3))
        firework.penup()
        firework.goto(0, 0)
        firework.setheading(angle)
        firework.pendown()
        firework.forward(length)
        screen.update()
    # 清除烟花痕迹
    firework.clear()
    firework.hideturtle()

# 主循环
while True:
    # 创建新的烟花
    new_firework = create_firework()
    # 发射烟花
    launch_firework(new_firework)