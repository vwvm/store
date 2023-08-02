import threading
import time


class work_a(threading.Thread):
    def __init__(self, name: str, event: threading.Event):
        super().__init__()
        self.name = name
        self.event = event


    def run(self) -> None:
        while not self.event.is_set():
            print(f"子线程{threading.current_thread().name}正在执行")
            time.sleep(1)




class work(threading.Thread):

    def __init__(self):
        super().__init__()

    def run(self) -> None:
        event_a = threading.Event()
        event_b = threading.Event()
        wa = work_a("这是A", event_a)
        wb = work_a("线程B", event_b)
        wa.daemon = True
        wb.daemon = True
        wa.start()
        wb.start()

        time.sleep(2)
        event_a.set()
        time.sleep(2)
        event_b.set()




def worker():
    while True:
        time.sleep(1)


if __name__ == "__main__":
    print("主线程开始")
    wok = work()

    wok.daemon = True
    wok.start()
    time.sleep(2)
    worker1 = threading.Thread(target=worker)
    worker1.start()

    # 主线程休眠5秒
    time.sleep(5)

    print("主线程结束")
