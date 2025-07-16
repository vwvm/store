import os
import subprocess
import time

import psutil
import pyautogui
import win32api
import win32con
import win32console
import win32gui
import win32process
from datetime import datetime


class ServerManager:
    """服务器管理类，负责与SCUM服务器交互"""

    def __init__(self, server_path):
        self.server_path = server_path

    def get_server_status(self):
        """获取服务器状态"""
        try:
            if not self.server_path:
                return "未配置路径"

            exe_name = os.path.basename(self.server_path)
            for proc in psutil.process_iter(['name', 'cmdline']):
                if proc.info['name'] == exe_name:
                    # 检查命令行参数是否包含 -log
                    cmdline = " ".join(proc.info['cmdline'] or [])
                    if "-log" in cmdline:
                        return "运行中"
            return "已停止"
        except Exception as e:
            return f"检查失败: {str(e)}"

    def send_ctrl_c(self, pid):
        """向Windows控制台进程发送Ctrl+C信号"""
        try:
            # 尝试查找进程的主窗口
            window_title = self.get_process_window_title(pid)
            if window_title:
                # 如果找到窗口，尝试激活窗口并发送Ctrl+C
                if self.activate_window_by_title(window_title):
                    # 等待窗口激活
                    time.sleep(0.5)

                    # 模拟按下Ctrl+C
                    pyautogui.hotkey('ctrl', 'c')
                    print(f"已向进程 {pid} 发送Ctrl+C信号")
                    return True

            # 如果窗口方法失败，尝试使用控制台附加方法（需要管理员权限）
            try:
                # 获取进程句柄
                process_handle = win32api.OpenProcess(
                    win32con.PROCESS_TERMINATE | win32con.PROCESS_QUERY_INFORMATION,
                    False,
                    pid
                )

                if not process_handle:
                    raise Exception(f"无法获取进程 {pid} 的句柄")

                # 尝试附加到进程的控制台
                if win32console.AttachConsole(pid):
                    # 附加成功后，向控制台发送Ctrl+C事件
                    win32api.GenerateConsoleCtrlEvent(win32con.CTRL_C_EVENT, 0)

                    # 释放控制台
                    win32console.FreeConsole()
                    win32api.CloseHandle(process_handle)
                    print(f"已向进程 {pid} 发送Ctrl+C信号")
                    return True

                win32api.CloseHandle(process_handle)

            except Exception as console_ex:
                print(f"控制台附加方法失败: {console_ex}")

            # 如果上述方法都失败，尝试使用任务管理器优雅终止
            print(f"所有方法失败，尝试优雅终止进程 {pid}")
            return self.graceful_terminate_process(pid)

        except Exception as e:
            print(f"发送Ctrl+C失败: {e}")
            return False

    def get_process_window_title(self, pid):
        """获取进程的窗口标题"""

        def enum_windows_callback(hwnd, ctx):
            if win32gui.IsWindowVisible(hwnd) and win32gui.IsWindowEnabled(hwnd):
                _, window_pid = win32process.GetWindowThreadProcessId(hwnd)
                if window_pid == pid:
                    ctx.append(win32gui.GetWindowText(hwnd))
            return True

        window_titles = []
        win32gui.EnumWindows(enum_windows_callback, window_titles)

        if window_titles:
            return window_titles[0]  # 返回第一个找到的窗口标题
        return None

    def activate_window_by_title(self, title):
        """激活指定标题的窗口"""
        hwnd = win32gui.FindWindow(None, title)
        if hwnd:
            # 确保窗口没有最小化
            if win32gui.IsIconic(hwnd):
                win32gui.ShowWindow(hwnd, win32con.SW_RESTORE)

            # 激活窗口
            win32gui.SetForegroundWindow(hwnd)
            return True
        return False

    def graceful_terminate_process(self, pid):
        """使用taskkill命令优雅终止进程"""
        try:
            import subprocess
            result = subprocess.run(
                ["taskkill", "/T", "/PID", str(pid)],
                capture_output=True,
                text=True
            )

            if result.returncode == 0:
                print(f"已优雅终止进程 {pid}")
                return True
            else:
                print(f"终止进程失败: {result.stderr}")
                return False

        except Exception as e:
            print(f"终止进程失败: {e}")
            return False

    def restart_server(self):
        """优雅地重启服务器（通过发送Ctrl+C而不是强制终止）"""
        try:
            # 1. 查找服务器进程
            exe_name = os.path.basename(self.server_path)
            server_process = None

            for proc in psutil.process_iter(['name', 'cmdline']):
                if proc.info['name'] == exe_name:
                    # 检查命令行参数是否包含 -log
                    cmdline = " ".join(proc.info['cmdline'] or [])
                    if "-log" in cmdline:
                        server_process = proc
                        break

            if server_process:
                # 2. 发送Ctrl+C信号
                self.log("发送Ctrl+C信号给服务器进程...")
                self.send_ctrl_c(server_process.pid)

                # 3. 等待进程终止
                timeout = 300  # 5分钟超时
                start_time = time.time()

                while time.time() - start_time < timeout:
                    if not server_process.is_running():
                        self.log("服务器已成功停止")
                        break
                    time.sleep(1)
                else:
                    self.log("等待服务器停止超时，尝试强制终止...")
                    try:
                        server_process.kill()
                        server_process.wait(timeout=10)
                        self.log("服务器已被强制终止")
                    except Exception as e:
                        self.log(f"强制终止服务器失败: {e}")
                        return False
            else:
                self.log("未找到运行中的服务器进程")

            # 4. 启动服务器
            self.log("正在启动服务器...")
            subprocess.Popen([self.server_path, "-log"], creationflags=subprocess.CREATE_NEW_CONSOLE)
            return True
        except Exception as e:
            self.log(f"重启服务器时出错: {str(e)}")
            return False

    def log(self, message):
        """记录日志"""
        timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        print(f"[{timestamp}] {message}")
