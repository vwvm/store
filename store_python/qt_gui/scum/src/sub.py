import subprocess
import sys

# 执行命令并捕获输出（不显示CMD窗口）
result = subprocess.run(
    ["dir", "/b"],  # 要执行的命令及其参数
    shell=True,      # 必须设置为True才能执行CMD内置命令
    capture_output=True,
    text=True
)

print(sys.platform)
print("返回码:", result.returncode)
print("输出内容:", result.stdout)