import sys
import logging
import time
import shutil
from watchdog.observers import Observer
from watchdog.observers.read_directory_changes import WindowsApiObserver
from watchdog.observers import api
from watchdog.events import FileSystemEventHandler

import FilePathUtil


class WatchHandler(FileSystemEventHandler):

    def __init__(self, watch_path, target_path):
        super().__init__()
        self.file_name = ""
        self.target_path = target_path
        self.watch_path = watch_path

    def on_any_event(self, event):
        pass

    def on_closed(self, event):
        print("on_closed\n")
        print(event.is_synthetic)

    def on_modified(self, event):
        pass

    def on_moved(self, event):
        pass

    def on_opened(self, event):
        print("on_opened\n")
        print(event.is_synthetic)

    def on_created(self, event):
        """
        创建文件或目录时调用。
        :param event:
        """
        logging.info(event.src_path)
        self.copy_program(event.src_path)
        if self.file_name != "" and self.file_name != event.src_path:
            # 启动复制程序
            pass
        self.file_name = event.src_path

    def on_deleted(self, event):
        logging.error("删除了")
        print(event.is_synthetic)

    def copy_program(self, file_name_to_copy: str):
        print(FilePathUtil.get_file_name())
        shutil.copy(file_name_to_copy, self.target_path)



class StartWatch(object):
    def __init__(self, watch_path, target_path="../target"):
        super().__init__()
        self.watch_path = watch_path
        self.target_path = target_path
        self.observer = Observer()
        self.thread_start(watch_path, target_path)

    def thread_start(self, watch_path: str, target_path: str):
        watch_handler = WatchHandler(watch_path, target_path)
        self.observer.schedule(watch_handler, watch_path, recursive=False)
        self.observer.start()
        pass

    def thread_info(self):
        print(self.observer.daemon)
        print("线程的活动状态:", self.observer.is_alive())


if __name__ == "__main__":
    print("*" * 50)
    logging.basicConfig(level=logging.INFO,
                        format='%(asctime)s - %(levelname)s - %(message)s',
                        datefmt='%Y-%m-%d %H:%M:%S')
    # 当前路径
    path = sys.argv[1] if len(sys.argv) > 1 else '.'
    print(path)
    app = StartWatch(path)
    app.thread_info()
    time.sleep(100000)
