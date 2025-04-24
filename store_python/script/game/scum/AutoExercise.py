import pyautogui
# import pydirectinput
import time
# pyautogui = pydirectinput

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
    # 找到第3个

    pyautogui.moveTo(1290, 965, duration=2)

    # pyautogui.moveRel(500, 500, duration=1)

    temp_num = 0

    for _ in range(10):
        for _ in range(10):
            for _ in range(10):
                pyautogui.keyDown(' ')
                pyautogui.sleep(0.2)
                pyautogui.keyUp(' ')
                pyautogui.sleep(0.2)
                print(temp_num)
                temp_num += 1
                pyautogui.keyDown('2')
                pyautogui.sleep(0.1)
                pyautogui.keyUp('2')
                pyautogui.sleep(0.3)

            pyautogui.moveTo(1290, 835, duration=0.5)
            pyautogui.sleep(0.1)

            pyautogui.keyDown('2')
            pyautogui.sleep(0.1)
            pyautogui.keyUp('2')
            pyautogui.sleep(0.3)

            pyautogui.moveTo(1290, 965, duration=0.5)
            pyautogui.sleep(0.1)

        pyautogui.moveTo(1290, 695, duration=0.5)
        pyautogui.sleep(0.1)

        pyautogui.keyDown('2')
        pyautogui.sleep(0.1)
        pyautogui.keyUp('2')
        pyautogui.sleep(0.3)

        pyautogui.moveTo(1290, 965, duration=0.5)
        pyautogui.sleep(0.1)

    pass


if __name__ == '__main__':
    # start()
    # pyautogui.FAILSAFE = False
    pyautogui.sleep(5)
    # start3()

    pass
