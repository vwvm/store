import numpy as np
import matplotlib.pyplot as plt

# 创建一个新的图形
plt.figure(figsize=(8, 8))

# 设置角度，从 0 到 360 度，每隔 30 度
angles_deg = np.arange(0, 360, 30)
angles_rad = np.deg2rad(angles_deg)  # 转换为弧度

# 绘制单位圆
theta = np.linspace(0, 2 * np.pi, 360)
x_circle = np.cos(theta)
y_circle = np.sin(theta)
plt.plot(x_circle, y_circle, color='black')

# 绘制射线和垂直线段
for angle in angles_rad:
    # 射线的终点坐标（单位圆上的点）
    x_end = np.cos(angle)
    y_end = np.sin(angle)

    # 绘制从原点到圆上点的射线
    plt.plot([0, x_end], [0, y_end], color='blue', linestyle='--')

    # 绘制垂直于 x 轴的线段（垂线到 x 轴）
    plt.plot([x_end, x_end], [0, y_end], color='red', linestyle=':')

    # 绘制垂直于 y 轴的线段（垂线到 y 轴）
    plt.plot([0, x_end], [y_end, y_end], color='green', linestyle=':')

    # 标注交点坐标
    plt.plot(x_end, y_end, 'ko')  # 圆上交点
    plt.text(x_end + 0.02, y_end + 0.02, f'({x_end:.2f}, {y_end:.2f})', fontsize=9)

# 设置坐标轴范围和比例
plt.xlim(-1.2, 1.2)
plt.ylim(-1.2, 1.2)
plt.gca().set_aspect('equal', adjustable='box')

# 添加网格和轴线
plt.grid(True, linestyle='--', alpha=0.5)
plt.axhline(0, color='black', linewidth=0.5)  # x轴
plt.axvline(0, color='black', linewidth=0.5)  # y轴

# 添加标题
plt.title('单位圆及每30度射线与垂线示意图')

# 显示图形
plt.show()
