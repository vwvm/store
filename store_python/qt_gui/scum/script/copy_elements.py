import sqlite3
import struct
from sqlite3 import Error
from typing import Optional, Union, Dict
import os


def create_connection() -> Optional[sqlite3.Connection]:
    """创建一个到SQLite数据库的连接，支持自定义路径"""
    default_db_path = 'SCUM.db'

    while True:
        custom_path = input(f"请输入数据库文件的完整路径（直接回车使用默认: {default_db_path}，q：退出）: ").strip()

        if custom_path.lower() == 'q':
            print("操作已取消")
            return None  # 返回None而不是exit(0)

        # 用户直接回车，使用默认路径
        if not custom_path:
            db_path = default_db_path
        else:
            db_path = custom_path

        # 检查文件是否存在
        if not os.path.exists(db_path):
            print(f"文件不存在: {db_path}")
            continue

        # 检查文件扩展名
        if not db_path.lower().endswith('.db'):
            print(f"文件类型不匹配，期望是.db文件: {db_path}")
            continue

        try:
            conn = sqlite3.connect(db_path)

            # 使用SQL查询获取SQLite版本，避免使用已弃用的sqlite3.version
            cursor = conn.cursor()
            cursor.execute('SELECT SQLITE_VERSION()')
            sqlite_version = cursor.fetchone()[0]

            print(f"成功连接到SQLite版本: {sqlite_version}")
            print(f"使用数据库: {os.path.abspath(db_path)}")
            return conn
        except Error as e:
            print(f"连接错误: {e}")
            print("请尝试其他路径")


def get_base_max_id(conn: Optional[sqlite3.Connection]) -> Union[int]:
    """查询base表中的最大id值"""
    cursor: sqlite3.Cursor = conn.cursor()
    cursor.execute("SELECT MAX(id) FROM base")
    result = cursor.fetchone()

    # 处理可能的NULL结果
    if result[0] is None:
        print("提示: 表中没有记录")
        return 0
    else:
        return int(result[0])


def get_base_element_max_id(conn: Optional[sqlite3.Connection]) -> Union[int]:
    """查询base_element表中的最大id值"""
    cursor: sqlite3.Cursor = conn.cursor()
    cursor.execute("SELECT MAX(element_id) FROM base_element")
    result = cursor.fetchone()

    # 处理可能的NULL结果
    if result[0] is None:
        print("提示: 表中没有记录")
        return 0
    else:
        return int(result[0])


def test(conn: Optional[sqlite3.Connection]) -> bool:
    pass


def query_user_id_by_steamid(conn: Optional[sqlite3.Connection], steamid: str) -> Optional[int]:
    """根据steamid查询user_profile表中的id（按creation_time排序，返回最新记录的id）"""
    if conn is None:
        print("错误: 没有提供数据库连接")
        return None

    try:
        cursor: sqlite3.Cursor = conn.cursor()
        # 直接查询id字段，而非所有字段
        query = """
                SELECT id
                FROM main.user_profile
                WHERE user_id = ?
                ORDER BY creation_time DESC
                LIMIT 1 \
                """
        cursor.execute(query, (steamid,))

        # 直接获取id值
        row = cursor.fetchone()

        if row is None:
            print(f"提示: 没有找到steamid为{steamid}的记录")
            return None

        # 返回id值（row[0]）
        return row[0]
    except Error as e:
        print(f"查询错误: {e}")
        return None


def get_valid_steamid(conn: sqlite3.Connection) -> str:
    """获取用户输入的有效SteamID"""
    while True:
        steamid = input("请输入SteamID (例如: 76561198000000000): ").strip()

        if not steamid:
            print("SteamID不能为空，请重新输入。")
            continue

        if len(steamid) != 17 or not steamid.isdigit():
            print("无效的SteamID格式，请输入17位数字的SteamID。")
            continue
        profile = query_user_id_by_steamid(conn, steamid)
        if profile is None:
            print(f"未找到steamid为{steamid}的记录，请重新输入。")
            continue
        return steamid


def select_base_by_user_id(conn: Optional[sqlite3.Connection], user_id: int) -> Optional[int]:
    """
    根据user_id查询base表中的所有基地，并让用户选择其中一个
    返回: 用户选择的基地ID，或None(如果没有找到基地或用户取消选择)
    """
    if conn is None:
        print("错误: 没有提供数据库连接")
        return None

    try:
        cursor = conn.cursor()
        # 查询base表中owner_user_profile_id等于user_id的所有记录
        query = """
                SELECT id, location_x, location_y, name
                FROM main.base
                WHERE owner_user_profile_id = ? \
                """
        cursor.execute(query, (user_id,))
        bases = cursor.fetchall()

        if not bases:
            print(f"提示: 没有找到user_id为{user_id}的用户拥有的基地")
            return None

        # 获取列名
        columns = [col[0] for col in cursor.description]

        # 打印基地列表供用户选择
        print(f"\n找到 {len(bases)} 个基地:")
        for i, base in enumerate(bases, 1):
            base_data = dict(zip(columns, base))
            print(f"{i}. 名称: {base_data['name']}")
            print(f"   位置: ({base_data['location_x']}, {base_data['location_y']})")
            print(f"   ID: {base_data['id']}")
            print("-" * 40)

        # 获取用户选择
        while True:
            choice = input(f"请选择基地 (1-{len(bases)}, 输入q退出): ").strip().lower()

            if choice == 'q':
                print("操作已取消")
                return None

            if not choice.isdigit():
                print("无效输入，请输入数字")
                continue

            choice_idx = int(choice) - 1
            if 0 <= choice_idx < len(bases):
                selected_base = dict(zip(columns, bases[choice_idx]))
                print(f"已选择基地: {selected_base['name']} (ID: {selected_base['id']})")
                return selected_base['id']
            else:
                print(f"无效选择，请输入1-{len(bases)}之间的数字")

    except Error as e:
        print(f"查询错误: {e}")
        return None


if __name__ == "__main__":
    conn = create_connection()
    if conn is None:
        exit(0)
    # 获取最大的max_base_id
    test(conn)
    max_base_id = get_base_max_id(conn)
    max_base_element = get_base_element_max_id(conn)
    steam_id = get_valid_steamid(conn)
    user_id = query_user_id_by_steamid(conn, steam_id)
    base_id = select_base_by_user_id(conn, user_id)
