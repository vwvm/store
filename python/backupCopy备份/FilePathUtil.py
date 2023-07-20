import os
import platform
import shutil


def path_join(dir_path: str, file_name: str, *path_tuple, **path_dict) -> str:
    """
    拼接路径
    :param dir_path: 路径名称
    :param file_name: 文件名称
    :return: 完整路径
    """
    return os.path.join(dir_path, file_name)


def path_splitting(path: str) -> list:
    path_list = []
    while True:
        (path, temp) = os.path.split(path)
        if temp == "":
            break
        path_list.append(temp)
    path_list.append(path)
    path_list.reverse()
    return path_list


if __name__ == '__main__':
    # 判断当前运行环境
    operating_system_category = platform.system().lower()
    source_path = os.getcwd()
    print(os.path.join(*path_splitting(source_path)))

    # 目前先实现在window环境下的复制
    if operating_system_category == "windows":
        drive_name, source_path = os.path.splitdrive(source_path)
        source_path_list = source_path.split(os.sep)
        print(source_path_list)
        source_path_list = os.path.split(source_path)
        print(source_path_list)
        old_path = os.path.join(drive_name, *source_path_list)
        print(old_path)
        old_path = os.path.join(old_path, "Main.py")
        source_path_list = source_path_list[:-1]
        final_path = os.path.join(drive_name, *source_path_list)
        new_path = os.path.join(final_path, "hhh")
        if not os.path.isdir(new_path):
            os.makedirs(new_path)
        new_path = os.path.join(new_path, "Main.py")
        shutil.copy(old_path, new_path)
