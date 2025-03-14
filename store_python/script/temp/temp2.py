import turtle
import random
import time

# 设置屏幕
screen = turtle.Screen()
screen.bgcolor("black")
screen.title("烟花模拟")
screen.setup(width=800, height=600)

# 创建一个烟花类
class Firework:
    def __init__(self):
        self.turtle = turtle.Turtle()
        self.turtle.shape("circle")
        self.turtle.color("white")
        self.turtle.penup()
        self.turtle.goto(0, -300)
        self.turtle.pendown()
        self.turtle.speed(0)
        self.turtle.shapesize(0.5, 0.5)
        self.exploded = False

    def launch(self):
        for _ in range(30):
            self.turtle.color(random.choice(["red", "orange", "yellow", "green", "blue", "purple"]))
            self.turtle.goto(random.randint(-400, 400), random.randint(-300, 300))
            time.sleep(0.05)
        self.exploded = True

    def explode(self):
        if self.exploded:
            for _ in range(50):
                self.turtle.color(random.choice(["red", "orange", "yellow", "green", "blue", "purple"]))
                self.turtle.goto(random.randint(-400, 400), random.randint(-300, 300))
                time.sleep(0.02)
            self.turtle.clear()

# 创建多个烟花
fireworks = [Firework() for _ in range(5)]

# 发射烟花
for firework in fireworks:
    firework.launch()
    firework.explode()

# 结束
turtle.done()