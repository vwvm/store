import os
import platform
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

    def __init__(self, origin_path, target_path):
        super().__init__()
        self.file_name = ""
        self.target_path = target_path
        self.origin_path = origin_path

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
        print(event.is_synthetic)

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
        """
        Called when a file or directory is deleted.
        :param event:
        """
        logging.error("删除了")
        print(event.is_synthetic)

    def copy_program(self, file_name_to_copy: str):
        """
        复制模块
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

        shutil.copy(old_path, new_path)


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
