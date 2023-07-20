import os
import platform
import shutil


def get_dir_path(path: str) -> str:
    """
    输入一个字符串形式的完整路径，获取目录名，如：path=D:/Python/密码生成工具_V1.3.2_20210526.exe,dir_path=D:/Python

    :param path:如：path=D:/Python/密码生成工具_V1.3.2_20210526.exe
    :return: D:/Python
    """
    dir_path = os.path.dirname(path)
    print('path={},dir_path={}'.format(path, dir_path))
    return dir_path


def get_file_name(path: str) -> str:
    """
    输入一个字符串形式的完整路径，获取文件名，如：path=D:/Python/密码生成工具_V1.3.2_20210526.exe,file_path=密码生成工具_V1.3.2_20210526.exe

    :param path:如：path=D:/Python/密码生成工具_V1.3.2_20210526.exe
    :return: 密码生成工具_V1.3.2_20210526.exe
    """
    file_path = os.path.basename(path)
    print('path={},file_path={}'.format(path, file_path))
    return file_path


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
