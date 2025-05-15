import threading

import pyautogui
# import pydirectinput
import time
# pyautogui = pydirectinput
from screeninfo import get_monitors
import keyboard


def forward_and_backward():
    for _ in range(10):
        pyautogui.keyDown('w')
        time.sleep(6)
        pyautogui.keyUp('w')
        time.sleep(0.3)
        pyautogui.keyDown('s')
        time.sleep(5.5)
        pyautogui.keyUp('s')
        time.sleep(0.3)


def start():
    while True:
        forward_and_backward()

        pyautogui.keyDown('x')
        time.sleep(0.3)
        pyautogui.keyUp('x')
        time.sleep(0.3)

        pyautogui.keyDown('7')
        time.sleep(0.3)
        pyautogui.keyUp('7')
        time.sleep(30)

        take_bag()

        forward_and_backward()

        pyautogui.keyDown('x')
        time.sleep(0.3)
        pyautogui.keyUp('x')
        time.sleep(0.3)

        take_bag()


def take_bag():
    pyautogui.keyDown('tab')
    time.sleep(0.1)
    pyautogui.keyUp('tab')
    time.sleep(0.1)
    pyautogui.moveRel(-1000, -1000, duration=1)
    pyautogui.moveRel(345, 195, duration=0.5)
    pyautogui.click(button='right')
    pyautogui.moveRel(0, 40, duration=0.5)
    pyautogui.click(button='left')
    pyautogui.keyDown('tab')
    time.sleep(0.1)
    pyautogui.keyUp('tab')
    time.sleep(0.1)


def start2():
    # pyautogui.keyDown('7')
    # pyautogui.sleep(0.1)
    # pyautogui.keyUp('7')
    # pyautogui.sleep(0.1)

    # pyautogui.moveRel(-1000, -1000, duration=1)
    pyautogui.keyDown('s')
    pyautogui.keyDown('w')
    pyautogui.sleep(1000)
    pyautogui.keyUp('s')
    pyautogui.keyUp('w')
    pyautogui.sleep(0.3)

# 解锁密码锁
def start3():
    try:
        # 获取所有屏幕信息
        monitors = get_monitors()
        if len(monitors) < 3:
            print("未找到第三个屏幕，请检查连接。")
            return
        # 获取第三个屏幕
        third_monitor = monitors[2]
        # 原始坐标
        original_x1 = 1290
        original_y1 = 965
        original_x2 = 1290
        original_y2 = 835
        original_x3 = 1290
        original_y3 = 695
        # 转换到第三个屏幕的坐标
        new_x1 = third_monitor.x + original_x1
        new_y1 = third_monitor.y + original_y1
        new_x2 = third_monitor.x + original_x2
        new_y2 = third_monitor.y + original_y2
        new_x3 = third_monitor.x + original_x3
        new_y3 = third_monitor.y + original_y3

        pyautogui.moveTo(new_x1, new_y1, duration=2)

        temp_num = 0
        time_per_space = 0.1

        for _ in range(10):
            for _ in range(10):
                for _ in range(10):
                    pyautogui.keyDown(' ')
                    pyautogui.sleep(time_per_space)
                    pyautogui.keyUp(' ')
                    pyautogui.sleep(time_per_space)
                    print(temp_num)
                    temp_num += 1
                    pyautogui.keyDown('2')
                    pyautogui.sleep(0.1)
                    pyautogui.keyUp('2')
                    pyautogui.sleep(0.1)

                pyautogui.moveTo(new_x2, new_y2, duration=0.5)
                pyautogui.sleep(0.1)

                pyautogui.keyDown('2')
                pyautogui.sleep(0.1)
                pyautogui.keyUp('2')
                pyautogui.sleep(0.1)

                pyautogui.moveTo(new_x1, new_y1, duration=0.5)
                pyautogui.sleep(0.1)

            pyautogui.moveTo(new_x3, new_y3, duration=0.5)
            pyautogui.sleep(0.1)

            pyautogui.keyDown('2')
            pyautogui.sleep(0.1)
            pyautogui.keyUp('2')
            pyautogui.sleep(0.1)

            pyautogui.moveTo(new_x1, new_y1, duration=0.5)
            pyautogui.sleep(0.1)

    except Exception as e:
        print(f"发生错误: {e}")


def press_key(key):
    """使用 PyAutoGUI 模拟按键"""
    pyautogui.press(key)
    print(f"按下了: {key}")


def listen_for_keys():
    """监听键盘输入并使用 PyAutoGUI 模拟按键"""
    print("开始监听键盘输入... (按 'q' 退出)")

    while True:
        event = keyboard.read_event()

        # 只处理按键按下事件
        if event.event_type == keyboard.KEY_DOWN:
            key = event.name

            # 退出条件
            if key == 'q':
                print("退出程序...")
                break

            press_key(key)

        time.sleep(0.01)  # 减少 CPU 使用率

def start4():
    # 创建并启动监听按键线程
    listener_thread = threading.Thread(target=listen_for_keys)
    listener_thread.daemon = True  # 设置为守护线程，主程序退出时自动退出
    listener_thread.start()

    # 保持主线程运行
    try:
        while listener_thread.is_alive():
            time.sleep(0.1)
    except KeyboardInterrupt:
        print("\n程序被用户中断")

if __name__ == '__main__':
    pyautogui.sleep(5)
    # start4()
    start3()
