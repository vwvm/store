import sqlite3
import struct
from sqlite3 import Error
from typing import Optional, Union, Dict, List
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


def select_base_by_coordinates(conn: Optional[sqlite3.Connection]) -> Optional[List[Union[int, float]]]:
    """
    根据用户输入的坐标查询base表中包含该坐标的基地，并让用户选择其中一个
    返回: [基地ID, location_x, location_y] 或 None(如果没有找到基地或用户取消选择)
    """
    if conn is None:
        print("错误: 没有提供数据库连接")
        return None

    # 获取用户输入的坐标
    coords = get_user_coordinates()
    if not coords:
        print("未获取到坐标，操作终止")
        return None

    x, y, _ = coords  # 忽略Z坐标，只需要X和Y

    try:
        cursor = conn.cursor()
        # 查询base表中包含该坐标的所有基地
        # 计算实际边界: location_x + bounds_{min/max}_x
        query = """
                SELECT id, \
                       location_x, \
                       location_y, \
                       name, \
                       bounds_min_x, \
                       bounds_min_y, \
                       bounds_max_x, \
                       bounds_max_y
                FROM main.base
                WHERE ? BETWEEN (location_x + bounds_min_x) AND (location_x + bounds_max_x)
                  AND ? BETWEEN (location_y + bounds_min_y) AND (location_y + bounds_max_y) \
                """
        cursor.execute(query, (x, y))
        bases = cursor.fetchall()

        if not bases:
            print(f"提示: 没有找到包含坐标 ({x}, {y}) 的基地")
            return None

        # 获取列名
        columns = [col[0] for col in cursor.description]

        # 打印基地列表供用户选择
        print(f"\n找到 {len(bases)} 个包含坐标 ({x}, {y}) 的基地:")
        for i, base in enumerate(bases, 1):
            base_data = dict(zip(columns, base))

            # 计算实际边界
            actual_min_x = base_data['location_x'] + base_data['bounds_min_x']
            actual_max_x = base_data['location_x'] + base_data['bounds_max_x']
            actual_min_y = base_data['location_y'] + base_data['bounds_min_y']
            actual_max_y = base_data['location_y'] + base_data['bounds_max_y']

            print(f"{i}. 名称: {base_data['name']}")
            print(f"   中心位置: ({base_data['location_x']}, {base_data['location_y']})")
            print(f"   实际边界: ({actual_min_x}, {actual_min_y}) 到 ({actual_max_x}, {actual_max_y})")
            print(f"   ID: {base_data['id']}")
            print("-" * 60)

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

                # 返回[id, x, y]列表
                return [
                    selected_base['id'],
                    selected_base['location_x'],
                    selected_base['location_y']
                ]
            else:
                print(f"无效选择，请输入1-{len(bases)}之间的数字")

    except Error as e:
        print(f"查询错误: {e}")
        return None


def get_user_coordinates() -> Optional[List[float]]:
    """
    获取用户输入的XYZ坐标
    支持两种输入格式：
    1. 分别输入X、Y、Z值
    2. 一次性输入格式如 "X=-507745.312 Y=151429.109 Z=37221.059" 的字符串
    返回: [x, y, z] 或 None(如果用户取消输入)
    """
    print("支持格式:")
    print("  1. 分别输入X、Y、Z值")
    print("  2. 一次性输入如 'X=-507745.312 Y=151429.109 Z=37221.059' 的字符串")

    while True:
        input_method = input("选择输入方式 (1: 分步输入, 2: 一次性输入, q: 退出): ").strip().lower()

        if input_method == 'q':
            print("操作已取消")
            return None

        if input_method == '1':  # 分步输入
            x_input = input("X坐标: ").strip().lower()
            if x_input == 'q':
                print("操作已取消")
                return None

            y_input = input("Y坐标: ").strip().lower()
            if y_input == 'q':
                print("操作已取消")
                return None

            z_input = input("Z坐标: ").strip().lower()
            if z_input == 'q':
                print("操作已取消")
                return None

            try:
                x = float(x_input)
                y = float(y_input)
                z = float(z_input)
                return [x, y, z]
            except ValueError:
                print("错误: 坐标必须是数字，请重新输入")
                continue

        elif input_method == '2':  # 一次性输入
            coords_input = input("请输入坐标字符串 (格式: X=值 Y=值 Z=值): ").strip().lower()

            if coords_input == 'q':
                print("操作已取消")
                return None

            try:
                # 解析坐标字符串
                parts = coords_input.split()
                if len(parts) != 3:
                    raise ValueError("格式错误，需要包含X、Y、Z三个值")

                x = float(parts[0].split('=')[1])
                y = float(parts[1].split('=')[1])
                z = float(parts[2].split('=')[1])

                return [x, y, z]
            except (ValueError, IndexError) as e:
                print(f"错误: 无效的坐标格式。{str(e)}")
                print("请使用格式如 'X=-507745.312 Y=151429.109 Z=37221.059' 的字符串")
                continue

        else:
            print("无效选择，请输入1、2或q")


def copy_base_with_new_coordinates(
        conn: Optional[sqlite3.Connection],
        base_info: List[Union[int, float]],
        new_coordinates: List[float],
        max_id: int
) -> Optional[int]:
    """
    复制基地记录并更新坐标和ID
    参数:
        conn: 数据库连接
        base_info: 原始基地信息 [id, x, y]
        new_coordinates: 新坐标 [x, y, z]
        max_id: 当前最大ID值
    返回:
        新创建的基地ID 或 None(如果失败)
    """
    if conn is None:
        print("错误: 没有提供数据库连接")
        return None

    try:
        base_id, original_x, original_y = base_info
        new_x, new_y, new_z = new_coordinates
        new_id = max_id + 1

        cursor = conn.cursor()

        # 查询原始基地记录
        cursor.execute("SELECT * FROM main.base WHERE id = ?", (base_id,))
        original_base = cursor.fetchone()

        if not original_base:
            print(f"错误: 找不到ID为{base_id}的基地记录")
            return None

        # 获取列名
        columns = [col[0] for col in cursor.description]

        # 创建新记录字典
        new_base = dict(zip(columns, original_base))

        # 更新ID和坐标
        new_base['id'] = new_id
        new_base['location_x'] = new_x
        new_base['location_y'] = new_y
        new_base["name"] = "GM #" + str(new_id)

        # 构建插入SQL
        placeholders = ', '.join(['?' for _ in new_base])
        columns_str = ', '.join(new_base.keys())
        query = f"INSERT INTO main.base ({columns_str}) VALUES ({placeholders})"

        # 执行插入
        cursor.execute(query, list(new_base.values()))
        conn.commit()

        print(f"成功创建新Base:")
        print(f"ID: {new_id}")
        print(f"位置: ({new_x}, {new_y}, {new_z if 'location_z' in new_base else 'N/A'})")

        return new_id

    except Error as e:
        print(f"数据库错误: {e}")
        conn.rollback()  # 回滚事务
        return None


def copy_base_elements(
        conn: Optional[sqlite3.Connection],
        old_base_id: int,
        new_base_id: int,
        target_coordinates: List[float],
        max_element_id: int
) -> int:
    """
    复制基地元素并根据基准位置调整坐标
    参数:
        conn: 数据库连接
        old_base_id: 原始基地ID
        new_base_id: 新基地ID
        target_coordinates: 目标坐标 [x, y, z]
        max_element_id: 当前最大元素ID
    返回:
        成功复制的元素数量
    """
    if conn is None:
        print("错误: 没有提供数据库连接")
        return 0

    try:
        cursor = conn.cursor()

        # 查询原始基地的所有元素
        cursor.execute("SELECT * FROM main.base_element WHERE base_id = ?", (old_base_id,))
        elements = cursor.fetchall()

        if not elements:
            print(f"提示: 没有找到base_id为{old_base_id}的元素")
            return 0

        # 获取列名
        columns = [col[0] for col in cursor.description]

        # 查找基准元素 (asset路径匹配)
        reference_element = None
        for element in elements:
            element_data = dict(zip(columns, element))
            if element_data.get(
                    'asset') == '/Game/ConZ_Files/BaseBuilding/BaseElements/BP_Base_Flag_Supporter.BP_Base_Flag_Supporter_C':
                reference_element = element_data
                break

        if not reference_element:
            print("错误: 没有找到基准元素 (BP_Base_Flag_Supporter)")
            return 0

        # 提取基准元素的原始坐标
        ref_x = reference_element.get('location_x', 0)
        ref_y = reference_element.get('location_y', 0)
        ref_z = reference_element.get('location_z', 0)

        # 目标坐标
        target_x, target_y, target_z = target_coordinates

        # 计算偏移量
        offset_x = target_x - ref_x
        offset_y = target_y - ref_y
        offset_z = target_z - ref_z

        print(f"基准元素原始坐标: ({ref_x}, {ref_y}, {ref_z})")
        print(f"目标坐标: ({target_x}, {target_y}, {target_z})")
        print(f"计算的偏移量: ({offset_x}, {offset_y}, {offset_z})")

        # 准备复制的元素列表
        elements_to_insert = []
        new_id = max_element_id + 1

        for element in elements:
            element_data = dict(zip(columns, element))

            # 更新ID
            element_data['element_id'] = new_id
            new_id += 1

            # 更新base_id
            element_data['base_id'] = new_base_id

            # 清空owner_profile_id和creator_prisoner_id
            element_data['owner_profile_id'] = None
            element_data['creator_prisoner_id'] = None

            # 应用坐标偏移
            if 'location_x' in element_data:
                element_data['location_x'] = float(element_data['location_x']) + offset_x
            if 'location_y' in element_data:
                element_data['location_y'] = float(element_data['location_y']) + offset_y
            if 'location_z' in element_data:
                element_data['location_z'] = float(element_data['location_z']) + offset_z

            elements_to_insert.append(element_data)

        # 批量插入新元素
        if elements_to_insert:
            # 构建插入SQL
            columns_str = ', '.join(elements_to_insert[0].keys())
            placeholders = ', '.join(['?' for _ in elements_to_insert[0]])
            query = f"INSERT INTO main.base_element ({columns_str}) VALUES ({placeholders})"

            # 执行批量插入
            cursor.executemany(query, [list(e.values()) for e in elements_to_insert])
            conn.commit()

            print(f"成功复制 {len(elements_to_insert)} 个基地元素到新基地")
            return len(elements_to_insert)
        else:
            print("没有元素需要复制")
            return 0

    except Error as e:
        print(f"数据库错误: {e}")
        conn.rollback()  # 回滚事务
        return 0


if __name__ == "__main__":
    conn = create_connection()
    if conn is None:
        exit(0)
    # 获取最大的max_base_id
    test(conn)
    # 获取最大的base_id
    max_base_id = get_base_max_id(conn)
    # 获取最大的建筑模块id
    max_base_element_id = get_base_element_max_id(conn)
    # 原来steam_id
    old_steam_id = get_valid_steamid(conn)
    # 用户id
    user_id = query_user_id_by_steamid(conn, old_steam_id)
    #
    print("请输入原本的坐标")
    old_base_info = select_base_by_coordinates(conn)
    print("查找到old_base_info:", old_base_info)
    print("请输入目标的坐标")
    bast_to_coordinates = get_user_coordinates()
    new_base_id = copy_base_with_new_coordinates(conn, old_base_info, bast_to_coordinates, max_base_id)
    copy_base_elements(conn, old_base_info[0], new_base_id, bast_to_coordinates, max_base_element_id)
    print(old_base_info)
    print(bast_to_coordinates)
