import pygame
import random
import math

# 初始化pygame
pygame.init()

# 设置屏幕尺寸和背景色
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600
BG_COLOR = (0, 0, 0)
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
pygame.display.set_caption("烟花模拟")

# 定义烟花类
class Firework:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.size = random.randint(1, 2)
        self.color = (random.randint(0, 255), random.randint(0, 255), random.randint(0, 255))
        self.velocity_y = random.uniform(5, 8)
        self.gravity = 0.05
        self.particles = []
        self.exploded = False
        self.Rand_height = random.randint(50, int(SCREEN_HEIGHT / 2))

    def move(self):
        if not self.exploded:
            self.velocity_y -= self.gravity
            self.y -= self.velocity_y
            if self.y <= self.Rand_height:
                self.explode()
        else:
            for particle in self.particles:
                particle.move()

    def explode(self):
        self.exploded = True
        for _ in range(50):  # 每个烟花产生50个粒子
            self.particles.append(Particle(self.x, self.y, self.color))

    def draw(self):
        if not self.exploded:
            pygame.draw.circle(screen, self.color, (int(self.x), int(self.y)), self.size)
        else:
            for particle in self.particles:
                particle.draw()

# 定义粒子类
class Particle:
    def __init__(self, x, y, color):
        self.x = x
        self.y = y
        self.color = color
        self.size = random.randint(1, 2)
        self.velocity = [random.uniform(-1, 1), random.uniform(-1, 1)]
        self.lifetime = 100  # 粒子生命周期

    def move(self):
        self.x += self.velocity[0]
        self.y += self.velocity[1]
        self.lifetime -= 1

    def draw(self):
        if self.lifetime > 0:
            pygame.draw.circle(screen, self.color, (int(self.x), int(self.y)), self.size)

# 主程序
def main():
    pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
    clock = pygame.time.Clock()
    fireworks = []

    running = True
    while running:
        screen.fill(BG_COLOR)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False

        if random.random() < 0.02:  # 以2%的概率生成新烟花
            x = random.randint(0, SCREEN_WIDTH)
            y = SCREEN_HEIGHT
            fireworks.append(Firework(x, y))

        for firework in fireworks:
            firework.move()
            firework.draw()

        pygame.display.flip()
        clock.tick(60)  # 设置帧率为60帧每秒

    pygame.quit()

if __name__ == "__main__":
    main()
