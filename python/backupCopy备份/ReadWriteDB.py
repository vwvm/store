#!/usr/bin/env python
# coding=utf-8
import sqlite3
from typing import Union


def examine_data() -> int:
    """
    :rtype: int
    返回0成功，返回1表示没有数据库
    """
    # 连接到数据库（如果不存在则会创建）
    conn = sqlite3.connect('database.db')
    # 创建一个游标对象
    cursor = conn.cursor()
    # 如果没有表创建一个名为 "group_path" 的表
    # group_seq 组别，origin_path原路径target_path目标路径delete_flag删除标记
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS group_path (
            id INTEGER PRIMARY KEY,
            group_seq INTEGER, 
            origin_path TEXT DEFAULT '-1',
            target_path TEXT DEFAULT '-1',
            delete_flag INTEGER DEFAULT 0
        )
    ''')
    conn.commit()
    conn.close()
    return 0


# 连接到数据库（如果不存在则会创建）
def save_data(path_config_list: list[list[Union[int, list[str]]]]):
    """
    写入数据
    :param path_config_list: list[list[int, list[str], list[str]]]
    int: 序号
    list[str]: 原始路径数组
    list[str]: 目标路径数组
    """
    # 连接到数据库（如果不存在则会创建）
    conn = sqlite3.connect('database.db')
    # 创建一个游标对象
    cursor = conn.cursor()

    for path_config in path_config_list:
        for origin_path in path_config[1]:
            cursor.execute("INSERT INTO group_path (group_seq, origin_path) VALUES (?,  ?)",
                           (path_config[0], origin_path))
        conn.commit()
        for target_path in path_config[2]:
            cursor.execute("INSERT INTO group_path (group_seq, target_path) VALUES (?,  ?)",
                           (path_config[0], target_path))
        conn.commit()

    # 提交更改并关闭连接
    conn.commit()
    conn.close()


def read_data() -> []:
    # 连接到数据库（如果不存在则会创建）
    conn = sqlite3.connect('database.db')
    # 创建一个游标对象
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM group_path ORDER BY group_seq")
    res = cursor.fetchall()
    if len(res) < 2:
        return []

    # 路径配置文件列表
    path_config_list: list[list[Union[int, list[str]]]] = []
    # 一组路径列表
    group_list: list[Union[int, list[str]]] = []
    origin_list = []
    target_list = []
    for i in res:
        temp_group_number = i[1]
        # 第一个保存序号
        if len(group_list) == 0:
            group_list.append(temp_group_number)
        # 出现临时的数据大于时，启动接下来的函数
        if temp_group_number > group_list[0]:
            group_list.append(origin_list)
            group_list.append(target_list)
            path_config_list.append(group_list)
            group_list = []
            origin_list = []
            target_list = []
        if i[2] != '-1':
            origin_list.append(i[2])
        if i[3] != "-1":
            target_list.append(i[3])
    # 保存最后一个
    group_list.append(origin_list)
    group_list.append(target_list)
    path_config_list.append(group_list)

    # 提交更改并关闭连接
    conn.commit()
    conn.close()
    return path_config_list
    pass


def clean_data():
    # 连接到数据库（如果不存在则会创建）
    conn = sqlite3.connect('database.db')
    # 创建一个游标对象
    cursor = conn.cursor()
    cursor.execute("DELETE FROM group_path")

    conn.commit()
    conn.close()
    return None


if __name__ == '__main__':
    clean_data()
    pass
