import turtle
import random
import time

# 初始化屏幕
screen = turtle.Screen()
screen.setup(800, 600)
screen.bgcolor("black")
screen.title("烟花绽放")
screen.tracer(0)  # 关闭自动刷新

# 创建烟花粒子列表
particiles = []


# 烟花粒子类
class Particle:
    def __init__(self, x, y, vx, vy, color):
        self.turtle = turtle.Turtle()
        self.turtle.penup()
        self.turtle.color(color)
        self.turtle.shape("circle")
        self.turtle.shapesize(0.3)
        self.turtle.goto(x, y)
        self.vx = vx  # x轴速度
        self.vy = vy  # y轴速度
        self.age = 0  # 粒子存在时间


# 创建新烟花
def create_firework():
    # 初始位置在屏幕底部随机位置
    x = random.randint(-300, 300)
    y = -250

    # 创建上升阶段的烟花
    firework = Particle(x, y, 0, 3, "orange")
    particiles.append(firework)


# 爆炸效果
def explode(x, y):
    colors = ["red", "yellow", "cyan", "pink", "white", "orange", "green"]
    for _ in range(50):  # 产生50个粒子
        angle = random.uniform(0, 360)
        speed = random.uniform(1, 5)
        vx = speed * (1 if random.random() > 0.5 else -1)
        vy = speed * (1 if random.random() > 0.5 else -1)
        color = random.choice(colors)
        particiles.append(Particle(x, y, vx, vy, color))


# 更新粒子状态
def update_particles():
    to_remove = []
    for p in particiles:
        # 应用重力
        p.vy -= 0.1
        # 更新位置
        p.turtle.goto(p.turtle.xcor() + p.vx, p.turtle.ycor() + p.vy)
        p.age += 1

        # 判断是否需要爆炸（上升阶段）
        if p.age == 30 and p.turtle.color()[0] == "orange":
            explode(p.turtle.xcor(), p.turtle.ycor())
            to_remove.append(p)

        # 移除旧粒子
        if p.age > 50:
            p.turtle.clear()
            p.turtle.hideturtle()
            to_remove.append(p)

    # 删除需要移除的粒子
    for p in to_remove:
        particiles.remove(p)


# 主循环
while True:
    # 随机生成新烟花
    if random.random() < 0.05:  # 控制生成频率
        create_firework()

    update_particles()
    screen.update()  # 手动刷新画面
    time.sleep(0.02)  # 控制动画速度

screen.mainloop()