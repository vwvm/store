#!/usr/bin/env python
# coding=utf-8


if __name__ == '__main__':
    import turtle

    # 设置画笔
    t = turtle.Turtle()
    t.speed(0)
    t.pensize(2)
    t.color("red")

    # 绘制爱心
    t.begin_fill()
    t.left(140)
    t.forward(224)
    for _ in range(200):
        t.right(1)
        t.forward(2)
    t.left(120)
    for _ in range(200):
        t.right(1)
        t.forward(2)
    t.forward(224)
    t.end_fill()

    # 隐藏画笔
    t.hideturtle()

    # 显示结果
    turtle.done()
