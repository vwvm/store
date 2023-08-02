import asyncio
import os
import platform
import sys
import logging
import threading
import time
import shutil
from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler
from queue import Queue
from concurrent.futures import ThreadPoolExecutor
import FilePathUtil


class FilePathQueueThread(threading.Thread):
    """
    新建的线程
    用于确保新文件复制完全后
    才进行移动和复制
    """

    def __init__(self, file_path_queue: Queue, target_path, event: threading.Event):
        super().__init__()
        self.target_path = target_path
        self.file_path_queue = file_path_queue
        self.file_path_temp = None
        # 创建线程池
        self.file_path_pool = ThreadPoolExecutor(max_workers=5)
        self.event = event
        pass

    def run(self) -> None:
        while not self.event.is_set():
            file_path = self.file_path_queue.get()
            if file_path is None:
                continue
            if file_path == self.file_path_temp:
                # 如果相同则跳过
                continue
            # 如果不同，则开始复制
            self.file_path_pool.submit(self.copy_program, file_path, self.event)
        pass

    def copy_program(self, file_name_to_copy: str, event: threading.Event):
        """
        复制模块
        :param event: 线程事件,用于停止线程
        :param file_name_to_copy: 等待复制的文件名称
        """
        # 判断当前运行环境
        operating_system_category = platform.system().lower()
        # 当前路径
        source_path = os.getcwd()
        if not os.path.isdir(self.target_path):
            os.makedirs(self.target_path)
        # 拆分路径，更好处理
        target_path_list = FilePathUtil.path_splitting(self.target_path)
        origin_path_list = FilePathUtil.path_splitting(file_name_to_copy)
        target_path_list.append(origin_path_list[-1])
        # 合并路径
        old_path = os.path.join(*origin_path_list)
        new_path = os.path.join(*target_path_list)

        logging.info(old_path)
        logging.info(new_path)
        while not event.is_set():
            try:
                shutil.copy2(old_path, new_path)
                break
            except Exception as r:
                logging.error(r)
                time.sleep(1)


class WatchHandler(FileSystemEventHandler):

    def __init__(self, origin_path, target_path, stop_event: threading.Event):
        """
        初始化监视器
        :param origin_path: 源路径
        :param target_path: 目标路径
        :param event: 停止事件
        """
        super().__init__()
        self.file_name = ""
        self.target_path = target_path
        self.origin_path = origin_path
        self.origin_path_sign = ""
        self.target_path_sign = ""
        self.stop_event = stop_event

        # 创建与子线程通讯
        self.file_path_queue = Queue()
        file_path_queue_thread = FilePathQueueThread(self.file_path_queue, self.target_path, stop_event)
        # 设置为守护线程
        file_path_queue_thread.daemon = True
        # 启动线程
        file_path_queue_thread.start()

    def on_any_event(self, event):
        """
        捕获全部事件
        :param event:
        """
        pass

    def on_closed(self, event):
        """
        当为写入而打开的文件关闭时调用。
        :param event:
        """
        print("on_closed\n")

    def on_modified(self, event):
        """
        在修改文件或目录时调用。
        :param event:
        """
        pass

    def on_moved(self, event):
        """
        在移动或重命名文件或目录时调用。
        :param event:
        """
        pass

    def on_opened(self, event):
        print("on_opened\n")

    def on_created(self, event):
        """
        创建文件或目录时调用。
        :param event:
        """
        logging.info(event.src_path)
        self.file_path_queue.put(event.src_path)

    def on_deleted(self, event):
        """
        Called when a file or directory is deleted.
        :param event:
        """
        logging.error("删除了")
        print(event.is_synthetic)


class StartWatch(object):
    """
    启动监视程序
    """

    def __init__(self, watch_path, target_path="../target"):
        super().__init__()
        self.watch_path = watch_path
        self.target_path = target_path
        self.stop_event = threading.Event()
        self.observer = Observer()
        self.thread_start(watch_path, target_path)

    def thread_start(self, watch_path: str, target_path: str):
        """
        线程启动
        :param watch_path: 监视路径
        :param target_path: 目标路径
        """
        watch_handler = WatchHandler(watch_path, target_path, stop_event)
        self.observer.schedule(watch_handler, watch_path, recursive=False)
        self.observer.start()
        pass

    def thread_info(self):
        """
        打印线程信息
        """
        print(self.observer.daemon)
        print("线程的活动状态:", self.observer.is_alive())

    def thread_stop(self):
        self.stop_event.set()
        self.observer.stop()
        self.thread_info()


if __name__ == "__main__":
    print("*" * 50)
    logging.basicConfig(level=logging.INFO,
                        format='%(asctime)s - %(levelname)s - %(message)s',
                        datefmt='%Y-%m-%d %H:%M:%S')
    # 当前路径
    path = sys.argv[1] if len(sys.argv) > 1 else '.'
    app = StartWatch(path)
    app.thread_info()
    time.sleep(100000)
