import os
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


def path_splicing(dir_path: str, file_name: str) -> str:
    return os.path.join(path, file_name)


if __name__ == '__main__':
    path = os.getcwd()
    print(os.path.splitdrive(path))
    dir_list = path.split(os.sep)
    final_path = os.path.join(*dir_list)
    old_path = os.path.join(path, "Main.py")
    new_path = os.path.join(path, "hhh")
    if not os.path.isdir(new_path):
        os.makedirs(new_path)
    new_path = os.path.join(new_path, "Main.py")

    shutil.copy(old_path, new_path)

    print(path)
