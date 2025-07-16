import sqlite3
import struct
from typing import Optional


def test(conn: Optional[sqlite3.Connection]) -> bool:
    """查询body_simulation字段并查找特定属性后的DoubleProperty后[15,19]位数据"""
    index_a_b = [9, 17]  # 提取前4字节（15-18位置）

    if conn is None:
        print("错误: 数据库连接为空")
        return False

    try:
        cursor = conn.cursor()
        cursor.execute("SELECT body_simulation FROM main.prisoner WHERE id = 2")
        result = cursor.fetchone()

        if result and result[0]:
            data = result[0]
            print(f"body_simulation的十六进制表示（前100字节）:\n{data[:100].hex()}")

            # 需要查找的属性名列表
            target_properties = [
                b'BaseStrength\x00',
                b'BaseConstitution\x00',
                b'BaseDexterity\x00',
                b'BaseIntelligence\x00'
            ]

            for property_name in target_properties:
                # 查找属性名位置
                property_pos = data.find(property_name)

                if property_pos != -1:
                    print(f"\n找到属性: {property_name.decode('utf-8')[:-1]}，位置: {property_pos}")

                    # 从属性名后开始查找DoubleProperty
                    search_start = property_pos + len(property_name)
                    double_pos = data.find(b'DoubleProperty\x00', search_start)

                    if double_pos != -1:
                        print(f"找到DoubleProperty，位置: {double_pos}")

                        # 计算DoubleProperty之后的位置
                        value_start = double_pos + len(b'DoubleProperty\x00')
                        data_start = value_start + index_a_b[0]  # 数据起始位置
                        data_end = value_start + index_a_b[1]  # 数据结束位置

                        # 检查是否有足够的数据
                        if data_end <= len(data):
                            value_bytes = data[data_start: data_end]
                            print(
                                f"DoubleProperty后[{index_a_b[0]},{index_a_b[1]})位数据的十六进制表示:\n{value_bytes.hex()}")

                            # 提取前8字节并按小端序解析为双精度浮点数
                            try:
                                # 确保有8字节数据
                                if len(value_bytes) >= 8:
                                    float_bytes = value_bytes[:8]
                                    # 小端序解析双精度浮点数
                                    float_value = struct.unpack('<d', float_bytes)[0]
                                    print(f"解析为双精度浮点数: {float_value}")
                                else:
                                    print(f"错误: 数据不足（仅{len(value_bytes)}字节，需要8字节）")
                            except Exception as e:
                                print(f"解析失败: {e}")
                        else:
                            print(
                                f"错误: 数据不足，无法提取[{index_a_b[0]},{index_a_b[1]})位（仅{len(data) - value_start}字节）")
                    else:
                        print(f"未在属性后找到DoubleProperty")
                else:
                    print(f"未找到属性: {property_name.decode('utf-8')[:-1]}")
            return True
        else:
            print("未找到body_simulation数据或数据为空")
            return False

    except sqlite3.Error as e:
        print(f"数据库错误: {e}")
        return False
    finally:
        if cursor:
            cursor.close()  # 确保游标被关闭


def update_property_value(conn: Optional[sqlite3.Connection], property_name: str, new_value: float) -> bool:
    """
    更新指定属性的DoubleProperty值

    参数:
    conn: SQLite数据库连接
    property_name: 要更新的属性名称(如BaseStrength)
    new_value: 要设置的十进制浮点数值
    """
    if conn is None:
        print("错误: 数据库连接为空")
        return False

    # 确保属性名以空字符结尾(与数据库中存储格式一致)
    property_bytes = f"{property_name}\x00".encode('utf-8')
    # 构建DoubleProperty搜索字节串
    double_property_bytes = b'DoubleProperty\x00'

    # 指数编码区间(前8字节，对应双精度浮点数)
    index_a_b = [9, 17]  # 提取前8字节(完整双精度浮点数)

    try:
        cursor = conn.cursor()

        # 1. 查询原始数据
        cursor.execute("SELECT body_simulation FROM main.prisoner WHERE id = 2")
        result = cursor.fetchone()

        if not result or not result[0]:
            print("未找到body_simulation数据或数据为空")
            return False

        data = bytearray(result[0])  # 转换为可修改的bytearray

        # 2. 查找属性位置
        property_pos = data.find(property_bytes)
        if property_pos == -1:
            print(f"未找到属性: {property_name}")
            return False

        print(f"\n找到属性: {property_name}，位置: {property_pos}")

        # 3. 查找DoubleProperty位置
        search_start = property_pos + len(property_bytes)
        double_pos = data.find(double_property_bytes, search_start)

        if double_pos == -1:
            print(f"未在属性后找到DoubleProperty")
            return False

        print(f"找到DoubleProperty，位置: {double_pos}")

        # 4. 计算数值应该写入的位置
        value_start = double_pos + len(double_property_bytes)
        data_start = value_start + index_a_b[0]  # 数据起始位置(完整双精度浮点数)

        # 5. 检查是否有足够的空间写入
        if data_start + 8 > len(data):  # 需要写入8字节(double)
            print(f"错误: 数据空间不足，无法在位置{data_start}写入8字节")
            return False

        # 6. 将十进制数转换为小端序双精度浮点数的8字节表示
        float_bytes = struct.pack('<d', new_value)

        # 7. 更新数据
        print(f"将位置{data_start}-{data_start + 7}的内容更新为: {float_bytes.hex()}")
        data[data_start:data_start + 8] = float_bytes

        # 8. 写入数据库
        cursor.execute("UPDATE main.prisoner SET body_simulation = ? WHERE id = 2", (bytes(data),))
        conn.commit()

        print(f"成功将{property_name}的值更新为: {new_value}")
        return True

    except sqlite3.Error as e:
        print(f"数据库错误: {e}")
        conn.rollback()  # 回滚事务
        return False
    except Exception as e:
        print(f"操作失败: {e}")
        conn.rollback()  # 回滚事务
        return False
    finally:
        if cursor:
            cursor.close()  # 确保游标被关闭


def main():
    """主函数: 查询属性值并允许用户修改"""
    try:
        # 连接数据库(请替换为实际数据库路径)
        db_path = input("请输入数据库路径(默认为prisoner.db): ").strip()
        if not db_path:
            db_path = "prisoner.db"

        conn = sqlite3.connect(db_path)

        # 可用属性列表
        properties = [
            'BaseStrength',
            'BaseConstitution',
            'BaseDexterity',
            'BaseIntelligence'
        ]

        while True:
            print("\n" + "=" * 50)
            print("监狱犯人属性编辑工具")
            print("=" * 50)

            # 查询当前值
            print("\n当前属性值:")
            if not test(conn):
                print("无法获取当前属性值，可能数据库连接失败或数据不存在")
                break

            # 用户输入
            print("\n请选择要执行的操作:")
            for i, prop in enumerate(properties, 1):
                print(f"{i}. 修改 {prop}")
            print("0. 退出")

            choice = input("\n请输入选项(0-4): ")
            if choice == '0':
                break

            if not choice.isdigit() or int(choice) not in range(1, len(properties) + 1):
                print("无效选择，请重新输入")
                continue

            property_index = int(choice) - 1
            property_name = properties[property_index]

            while True:
                new_value = input(f"\n请输入新的{property_name}值(十进制浮点数，输入'q'返回菜单): ")
                if new_value.lower() == 'q':
                    break

                try:
                    float_value = float(new_value)
                except ValueError:
                    print("输入不是有效的浮点数，请重新输入")
                    continue

                # 更新数据库
                if update_property_value(conn, property_name, float_value):
                    print("更新成功!")
                else:
                    print("更新失败!")

    except Exception as e:
        print(f"发生错误: {e}")
    finally:
        if conn:
            conn.close()  # 关闭数据库连接
            print("\n数据库连接已关闭")


if __name__ == "__main__":
    main()
