from script.ScumServer import win_start
import ctypes
import sys
import os
from ctypes import wintypes


def is_admin():
    """检查当前程序是否以管理员权限运行（修复版）"""
    try:
        # 显式加载shell32.dll并定义IsUserAnAdmin函数
        shell32 = ctypes.WinDLL("shell32", use_last_error=True)
        shell32.IsUserAnAdmin.argtypes = []
        shell32.IsUserAnAdmin.restype = ctypes.c_bool
        return shell32.IsUserAnAdmin()
    except Exception as e:
        print(f"权限检查出错: {e}")
        return False


def run_as_admin():
    """以管理员权限重新启动当前程序（修复 ShellExecuteW 调用）"""
    # 显式加载 shell32.dll 并定义 ShellExecuteW 函数原型
    shell32 = ctypes.WinDLL("shell32", use_last_error=True)
    shell32.ShellExecuteW.argtypes = [
        wintypes.HWND,
        wintypes.LPCWSTR,
        wintypes.LPCWSTR,
        wintypes.LPCWSTR,
        wintypes.LPCWSTR,
        ctypes.c_int
    ]
    shell32.ShellExecuteW.restype = wintypes.HINSTANCE

    # 获取当前脚本路径
    script_path = os.path.abspath(sys.argv[0])

    # 调用 ShellExecuteW 以管理员权限重启
    result = shell32.ShellExecuteW(
        None,  # 父窗口句柄
        "runas",  # 操作类型（以管理员运行）
        sys.executable,  # 要运行的程序（Python 解释器）
        script_path,  # 命令行参数（当前脚本路径）
        None,  # 工作目录
        1  # 显示窗口的方式（SW_SHOWNORMAL）
    )

    # 检查是否成功（返回值大于32表示成功）
    if result <= 32:
        print(f"提权失败，错误代码: {result}")


if __name__ == "__main__":
    # if not is_admin():
    #     print("当前不是管理员权限，正在请求提升...")
    #     run_as_admin()
    #     # 退出当前非管理员进程
    #     sys.exit(0)

    win_start()
