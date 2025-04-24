import pygame
import math
import random

# 初始化 Pygame
pygame.init()

# 设置窗口大小
width, height = 800, 600
screen = pygame.display.set_mode((width, height))
pygame.display.set_caption("Rotating Disc Game")

# 指定系统字体
font = pygame.font.SysFont("simhei", 36)


class InputBox:
    def __init__(self, x, y, w, h, text=''):
        self.rect = pygame.Rect(x, y, w, h)
        self.color = pygame.Color('lightskyblue3')
        self.text = text
        self.txt_surface = font.render(text, True, self.color)
        self.active = False

    def handle_event(self, event):
        if event.type == pygame.MOUSEBUTTONDOWN:
            if self.rect.collidepoint(event.pos):
                self.active = not self.active
            else:
                self.active = False
            self.color = pygame.Color('dodgerblue2') if self.active else pygame.Color('lightskyblue3')
        if event.type == pygame.KEYDOWN:
            if self.active:
                if event.key == pygame.K_RETURN:
                    self.active = False
                elif event.key == pygame.K_BACKSPACE:
                    self.text = self.text[:-1]
                elif event.unicode.isdigit() or event.unicode == '.':
                    self.text += event.unicode
                self.txt_surface = font.render(self.text, True, self.color)

    def update(self):
        width = max(200, self.txt_surface.get_width() + 10)
        self.rect.w = width

    def draw(self, screen):
        screen.blit(self.txt_surface, (self.rect.x + 5, self.rect.y + 5))
        pygame.draw.rect(screen, self.color, self.rect, 2)


class Checkbox:
    def __init__(self, x, y, size):
        self.rect = pygame.Rect(x, y, size, size)
        self.checked = False

    def handle_event(self, event):
        if event.type == pygame.MOUSEBUTTONDOWN:
            if self.rect.collidepoint(event.pos):
                self.checked = not self.checked

    def draw(self, screen):
        pygame.draw.rect(screen, (0, 0, 0), self.rect, 2)
        if self.checked:
            pygame.draw.line(screen, (0, 0, 0),
                             (self.rect.x, self.rect.y),
                             (self.rect.x + self.rect.width, self.rect.y + self.rect.height), 2)
            pygame.draw.line(screen, (0, 0, 0),
                             (self.rect.x, self.rect.y + self.rect.height),
                             (self.rect.x + self.rect.width, self.rect.y), 2)


# 游戏参数初始化
input_boxes = [
    InputBox(100, 50, 200, 32, text='30'),
    InputBox(100, 120, 200, 32, text='0.3'),
    InputBox(100, 190, 200, 32, text='30'),
    InputBox(100, 260, 200, 32, text='15'),
    InputBox(100, 330, 200, 32, text='5')
]
input_labels = ["游戏总时间", "鼠标灵敏度", "开启角度", "通过角度", "F键持续时间"]
show_angle_checkbox = Checkbox(100, 400, 20)
show_angle_label = font.render("显示角度区域", True, (0, 0, 0))


class GameState:
    START_SCREEN = 0
    GAME_RUNNING = 1
    GAME_SUCCESS = 2
    current_state = START_SCREEN


# 圆盘属性
disc_radius = 100
disc_center = (width // 2, height // 2)
rotation_angle = 0
target_angle = random.randint(0, 360)  # 改为360度范围
pointer_angle = 180  # 初始指向正上方

# 时间管理
game_timer = 0
f_timer = 0


def start_game():
    global rotation_angle, pointer_angle, target_angle, game_timer, f_timer
    GameState.current_state = GameState.GAME_RUNNING
    rotation_angle = 0
    pointer_angle = 180  # 重置指向正上方
    target_angle = random.randint(0, 360)
    game_timer = pygame.time.get_ticks()
    f_timer = 0
    pygame.mouse.set_visible(False)
    pygame.event.set_grab(True)


def calculate_angles():
    pass_angle = float(input_boxes[3].text)
    start_angle = float(input_boxes[2].text)

    pass_start = (target_angle - pass_angle / 2) % 360
    pass_end = (target_angle + pass_angle / 2) % 360
    start_start = (target_angle - (pass_angle + start_angle) / 2) % 360
    start_end = (target_angle + (pass_angle + start_angle) / 2) % 360
    return pass_start, pass_end, start_start, start_end


def draw_angle_indicator():
    pass_start, pass_end, start_start, start_end = calculate_angles()

    # 绘制通过区域（半透明绿色）
    pass_surface = pygame.Surface((disc_radius * 2, disc_radius * 2), pygame.SRCALPHA)
    pygame.draw.arc(pass_surface, (0, 255, 0, 100), (0, 0, disc_radius * 2, disc_radius * 2),
                    math.radians(pass_start), math.radians(pass_end), 10)
    screen.blit(pass_surface, (disc_center[0] - disc_radius, disc_center[1] - disc_radius))

    # 绘制开启区域边框（蓝色）
    pygame.draw.arc(screen, (0, 0, 255), (disc_center[0] - disc_radius, disc_center[1] - disc_radius,
                                          disc_radius * 2, disc_radius * 2), math.radians(start_start),
                    math.radians(start_end), 5)

    # 目标指示线
    target_x = disc_center[0] + disc_radius * math.cos(math.radians(target_angle))
    target_y = disc_center[1] - disc_radius * math.sin(math.radians(target_angle))
    pygame.draw.line(screen, (255, 0, 0), disc_center, (target_x, target_y), 3)


def handle_rotation():
    global rotation_angle, f_timer
    pass_start, pass_end, start_start, start_end = calculate_angles()
    current_angle = pointer_angle % 360

    # 判断是否在开启区域
    in_start_area = False
    if start_start < start_end:
        in_start_area = start_start <= current_angle <= start_end
    else:
        in_start_area = current_angle >= start_start or current_angle <= start_end

    if pygame.key.get_pressed()[pygame.K_f] and in_start_area:
        total_f_time = float(input_boxes[4].text)

        # 计算到通过区域的最小距离
        if pass_start < pass_end:
            distance = min(abs(current_angle - pass_start), abs(current_angle - pass_end))
        else:
            distance = min(abs(current_angle - pass_start), abs(current_angle - (pass_end + 360)))

        # 根据距离计算旋转比例（越近比例越高）
        max_distance = float(input_boxes[2].text) / 2  # 开启区域半径
        ratio = 1 - (distance / max_distance)
        ratio = max(0, min(1, ratio))  # 限制在0-1之间

        rotation_angle = min(90, rotation_angle + 3 * ratio)
        f_timer = min(f_timer + 1 / 60, total_f_time)
    else:
        # 缓慢回正
        rotation_angle = max(0, rotation_angle - 2)
        f_timer = max(0, f_timer - 1 / 60)


# 主循环
clock = pygame.time.Clock()
running = True

while running:
    screen.fill((255, 255, 255))

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

        if event.type == pygame.KEYDOWN:
            # 处理ESC键（新增：在任何游戏状态都可返回）
            if event.key == pygame.K_ESCAPE:
                GameState.current_state = GameState.START_SCREEN
                pygame.mouse.set_visible(True)
                pygame.event.set_grab(False)

            # 处理空格键
            if event.key == pygame.K_SPACE:
                if GameState.current_state == GameState.START_SCREEN:
                    try:
                        [float(box.text) for box in input_boxes]
                        start_game()
                    except:
                        pass
                elif GameState.current_state == GameState.GAME_SUCCESS:
                    start_game()

        # 处理鼠标移动（修改方向逻辑）
        if GameState.current_state == GameState.GAME_RUNNING:
            if event.type == pygame.MOUSEMOTION:
                # 获取水平移动量并反转方向
                mouse_rel = -event.rel[0] * float(input_boxes[1].text)  # 新增负号
                pointer_angle = (pointer_angle + mouse_rel) % 360

    # 界面渲染
    if GameState.current_state == GameState.START_SCREEN:
        for box in input_boxes:
            box.draw(screen)
        for i, label in enumerate(input_labels):
            screen.blit(font.render(label, True, (0, 0, 0)), (100, 50 + i * 70 - 30))

        show_angle_checkbox.draw(screen)
        screen.blit(show_angle_label, (130, 400))
        screen.blit(font.render("按空格开始游戏", True, (0, 0, 0)), (width - 200, 20))

    elif GameState.current_state == GameState.GAME_RUNNING:
        handle_rotation()

        # 绘制旋转圆盘
        disc_surface = pygame.Surface((disc_radius * 2, disc_radius * 2), pygame.SRCALPHA)
        pygame.draw.circle(disc_surface, (100, 100, 255), (disc_radius, disc_radius), disc_radius)
        rotated_disc = pygame.transform.rotate(disc_surface, rotation_angle)
        screen.blit(rotated_disc, rotated_disc.get_rect(center=disc_center))

        # 中心线
        angle_rad = math.radians(rotation_angle)
        start_pos = (
            disc_center[0] + math.cos(angle_rad) * disc_radius,
            disc_center[1] - math.sin(angle_rad) * disc_radius
        )
        end_pos = (
            disc_center[0] - math.cos(angle_rad) * disc_radius,
            disc_center[1] + math.sin(angle_rad) * disc_radius
        )
        pygame.draw.line(screen, (0, 0, 0), start_pos, end_pos, 5)

        # 绘制指针（角度已修正）
        pointer_end = (
            disc_center[0] + disc_radius * 1.2 * math.cos(math.radians(pointer_angle)),
            disc_center[1] - disc_radius * 1.2 * math.sin(math.radians(pointer_angle))
        )
        pygame.draw.line(screen, (255, 0, 0), disc_center, pointer_end, 5)

        if show_angle_checkbox.checked:
            draw_angle_indicator()

        elapsed = (pygame.time.get_ticks() - game_timer) / 1000
        time_text = font.render(
            f"时间: {float(input_boxes[0].text)-elapsed:.1f}s  F剩余: {float(input_boxes[4].text)-f_timer:.1f}s",
            True, (0,0,0))
        screen.blit(time_text, (20, 20))

        # 胜利条件检测...
        pass_start, pass_end = calculate_angles()[:2]
        current_angle = pointer_angle % 360
        in_pass = pass_start <= current_angle <= pass_end if pass_start < pass_end else current_angle >= pass_start or current_angle <= pass_end

    elif GameState.current_state == GameState.GAME_SUCCESS:
        screen.blit(font.render("游戏成功！", True, (0, 255, 0)), (width // 2 - 80, height // 2))
        screen.blit(font.render("按空格重新开始", True, (0, 0, 0)), (width // 2 - 100, height // 2 + 50))

    pygame.display.flip()
    clock.tick(60)

pygame.quit()