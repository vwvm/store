from PySide6.QtCore import QObject, Signal
import os
import sys
import subprocess
import urllib.request
import zipfile
import tempfile
from datetime import datetime


class SteamCMDManager(QObject):
    """SteamCMD管理工具核心逻辑，使用信号发送日志"""

    log_signal = Signal(str)  # 发送日志消息的信号

    def __init__(self):
        super().__init__()
        self.temp_dir = None
        self.download_url = None
        self.steamcmd_path = None
        self.is_windows = None

    def start(self):
        self.log_signal.emit("start")
        self.is_windows = sys.platform == "win32"
        self.steamcmd_path = self._get_steamcmd_path()
        self.download_url = self._get_download_url()
        self.temp_dir = tempfile.gettempdir()


    def _get_steamcmd_path(self):
        base_path = os.path.abspath(".")
        if self.is_windows:
            return os.path.join(base_path, "steamcmd.exe")
        else:
            return os.path.join(base_path, "steamcmd.sh")

    def _get_download_url(self):
        if self.is_windows:
            return "https://steamcdn-a.akamaihd.net/client/installer/steamcmd.zip"
        else:
            return "https://steamcdn-a.akamaihd.net/client/installer/steamcmd_linux.tar.gz"


    def check_steamcmd_exists(self):
        exists = os.path.exists(self.steamcmd_path)
        self.log_signal.emit(f"检查SteamCMD是否存在: {'✅ 存在' if exists else '❌ 不存在'}")
        return exists

    def make_executable(self):
        if not self.is_windows and not os.access(self.steamcmd_path, os.X_OK):
            self.log_signal.emit(f"设置 {self.steamcmd_path} 为可执行文件...")
            try:
                os.chmod(self.steamcmd_path, 0o755)
                self.log_signal.emit("✅ 权限设置成功")
                return True
            except Exception as e:
                self.log_signal.emit(f"❌ 权限设置失败: {str(e)}")
                return False
        return True

    def download_steamcmd(self):
        self.log_signal.emit(f"开始从 {self.download_url} 下载SteamCMD...")

        try:
            temp_file, _ = urllib.request.urlretrieve(self.download_url)

            if self.is_windows:
                with zipfile.ZipFile(temp_file, 'r') as zip_ref:
                    zip_ref.extractall(os.path.dirname(self.steamcmd_path))
            else:
                import tarfile
                with tarfile.open(temp_file, 'r:gz') as tar_ref:
                    tar_ref.extractall(os.path.dirname(self.steamcmd_path))

            self.log_signal.emit(f"SteamCMD已下载到 {self.steamcmd_path}")
            self.make_executable()
            return True

        except Exception as e:
            self.log_signal.emit(f"❌ 下载失败: {str(e)}")
            return False

    def update_steamcmd(self):
        self.log_signal.emit("开始更新SteamCMD...")

        if not self.check_steamcmd_exists():
            self.log_signal.emit("SteamCMD不存在，尝试下载...")
            if not self.download_steamcmd():
                self.log("❌ 下载失败，无法更新SteamCMD")
                return False

        cmd = [self.steamcmd_path, "+quit"]

        try:
            result = subprocess.run(
                cmd,
                capture_output=True,
                text=True,
                check=True,
                timeout=300
            )

            self.log_signal.emit("✅ SteamCMD更新成功！")
            return True

        except subprocess.CalledProcessError as e:
            self.log_signal.emit(f"❌ 更新失败: {e.stderr}")
            return False
        except subprocess.TimeoutExpired:
            self.log_signal.emit("❌ 更新超时（5分钟），请检查网络连接")
            return False
        except Exception as e:
            self.log_signal.emit(f"❌ 发生未知错误: {str(e)}")
            return False