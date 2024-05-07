import pyautogui


def forward_and_backward():
    for _ in range(10):
        pyautogui.keyDown('w')
        pyautogui.sleep(6)
        pyautogui.keyUp('w')
        pyautogui.sleep(0.3)
        pyautogui.keyDown('s')
        pyautogui.sleep(5.5)
        pyautogui.keyUp('s')
        pyautogui.sleep(0.3)


def start():
    while True:
        forward_and_backward()

        pyautogui.keyDown('x')
        pyautogui.sleep(0.3)
        pyautogui.keyUp('x')
        pyautogui.sleep(0.3)

        pyautogui.keyDown('7')
        pyautogui.sleep(0.3)
        pyautogui.keyUp('7')
        pyautogui.sleep(30)

        take_bag()

        forward_and_backward()

        pyautogui.keyDown('x')
        pyautogui.sleep(0.3)
        pyautogui.keyUp('x')
        pyautogui.sleep(0.3)

        take_bag()


def take_bag():
    pyautogui.keyDown('tab')
    pyautogui.sleep(0.1)
    pyautogui.keyUp('tab')
    pyautogui.sleep(0.1)
    pyautogui.moveRel(-1000, -1000, duration=1)
    pyautogui.moveRel(345, 195, duration=0.5)
    pyautogui.click(button='right')
    pyautogui.moveRel(0, 40, duration=0.5)
    pyautogui.click(button='left')
    pyautogui.keyDown('tab')
    pyautogui.sleep(0.1)
    pyautogui.keyUp('tab')
    pyautogui.sleep(0.1)


if __name__ == '__main__':
    # start()
    pyautogui.sleep(3)
    start()
    pass
