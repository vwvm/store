import sqlite3

def calculate_relative_bounds(base_x, base_y, x_count, y_count):
    """计算相对于中心点的边界坐标，确保最小值为±10000"""
    half_width = x_count * 150
    half_height = y_count * 150

    # 计算相对于中心点的最小和最大坐标
    rel_min_x = -half_width
    rel_min_y = -half_height
    rel_max_x = half_width
    rel_max_y = half_height

    # 确保边界不小于±10000
    return min(rel_min_x, -10000), min(rel_min_y, -10000), max(rel_max_x, 10000), max(rel_max_y, 10000)

def get_asset_by_level(level):
    """根据等级获取对应的建筑材质"""
    assets = {
        1: "/Game/ConZ_Files/BaseBuilding/BaseElements/Modular/BP_Base_Modular_Foundation_Twig.BP_Base_Modular_Foundation_Twig_C",
        2: "/Game/ConZ_Files/BaseBuilding/BaseElements/Modular/BP_Base_Modular_Foundation_Wood.BP_Base_Modular_Foundation_Wood_C",
        3: "/Game/ConZ_Files/BaseBuilding/BaseElements/Modular/BP_Base_Modular_Foundation_Stone.BP_Base_Modular_Foundation_Stone_C",
        4: "/Game/ConZ_Files/BaseBuilding/BaseElements/Modular/BP_Base_Modular_Foundation_Metal.BP_Base_Modular_Foundation_Metal_C",
        5: "/Game/ConZ_Files/BaseBuilding/BaseElements/Modular/BPC_Base_Modular_Foundation_Cement.BPC_Base_Modular_Foundation_Cement_C"
    }
    return assets.get(level, assets[1])  # 默认返回等级1的材质

def find_most_common_map_id(cursor):
    """查询base表中出现次数最多的map_id"""
    try:
        cursor.execute("""
            SELECT map_id, COUNT(*) as count 
            FROM base 
            GROUP BY map_id 
            ORDER BY count DESC 
            LIMIT 1
        """)
        result = cursor.fetchone()
        if result:
            print("找到base表记录，使用map_id={}".format(result[0]))
            return result[0]
        else:
            print("[警告] 未找到base表记录，使用默认map_id=3")
            return 3
    except sqlite3.Error as e:
        print(f"[警告] 查询map_id失败: {e}，使用默认map_id=3")
        return 3

def generate_elements():
    global conn
    db_path = input("请输入 Scum.db 文件路径: ").strip()
    if not db_path:
        db_path = r"C:\Users\BlackBox\AppData\Local\SCUM\Saved\SaveFiles\scum.db"

    try:
        # 连接到数据库
        conn = sqlite3.connect(db_path)
        cursor = conn.cursor()

        # 获取用户ID
        user_id = int(input("请输入用户ID: "))

        # 查询最大element_id
        cursor.execute("SELECT MAX(element_id) FROM base_element")
        max_element_id = cursor.fetchone()[0]
        if max_element_id is None:
            max_element_id = 6916  # 默认起始ID
        print(f"[日志] 找到base_element表的最大元素ID: {max_element_id}")

        # 查询最常用的map_id
        map_id = find_most_common_map_id(cursor)
        print(f"[日志] 使用最常用的map_id: {map_id}")

        # 获取基础坐标（现在作为中心点）
        base_x = float(input("请输入基地基准 X 坐标: "))
        base_y = float(input("请输入基地基准 Y 坐标: "))
        base_z = float(input("请输入基地 Z 坐标: "))

        # 获取生成数量
        x_count = int(input("请输入 X 方向元素数量 (建议30): "))
        y_count = int(input("请输入 Y 方向元素数量 (建议30): "))

        # 获取建筑等级
        while True:
            try:
                level = int(input("请输入建筑等级 (1-5): "))
                if 1 <= level <= 5:
                    break
                else:
                    print("等级必须在1-5之间")
            except ValueError:
                print("请输入有效的数字")

        asset = get_asset_by_level(level)
        print(f"已选择建筑材质: 等级{level} - {asset}")

        # 计算base表相关值
        new_base_id = max_element_id + 1

        # 计算相对于旗帜的边界
        rel_min_x, rel_min_y, rel_max_x, rel_max_y = calculate_relative_bounds(base_x, base_y, x_count, y_count)
        # 计算旗帜中心位置
        center_x = base_x + (x_count - 1) * 150
        center_y = base_y + (y_count - 1) * 150

        # 确认操作
        total_elements = x_count * y_count
        confirm = input(f"""
        即将执行以下操作:
        1. 生成 {total_elements} 个等级{level} 建筑元素到 base_element 表
        2. 添加旗帜支撑物元素 (ID: {new_base_id + total_elements + 1})
        3. 在 base 表插入记录:
           - ID: {new_base_id}
           - 中心位置: ({center_x}, {center_y})
           - Map ID: {map_id}
           - 相对边界: X [{rel_min_x}, {rel_max_x}], Y [{rel_min_y}, {rel_max_y}]

        是否继续? (y/n): """).lower()

        if confirm != 'y':
            print("操作已取消")
            return

        # 开始事务
        conn.execute("BEGIN TRANSACTION")

        # 插入base表记录，使用查询到的map_id
        base_name = f"Base #{new_base_id}"
        cursor.execute("""
                       INSERT INTO base (id, location_x, location_y, size_x, size_y,
                                         name, map_id, user_profile_id, owner_user_profile_id,
                                         is_owned_by_player, bounds_min_x, bounds_min_y, bounds_max_x, bounds_max_y)
                       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                       """, (
                           new_base_id, center_x, center_y, 0, 0,
                           base_name, map_id, user_id, user_id,
                           1, rel_min_x, rel_min_y, rel_max_x, rel_max_y
                       ))

        # 生成并插入元素
        elements_to_insert = []

        # 普通建筑元素
        for x in range(x_count):
            for y in range(y_count):
                element_id = new_base_id + x * y_count + y
                location_x = base_x + (x * 300)
                location_y = base_y + (y * 300)

                elements_to_insert.append((
                    element_id, new_base_id, location_x, location_y, base_z,
                    0, 0, 0, 1, 1, 1,
                    asset, 1, user_id, 1, user_id
                ))

        # 添加旗帜支撑物元素
        flagpole_id = new_base_id + x_count * y_count + 1
        flagpole_asset = "/Game/ConZ_Files/BaseBuilding/BaseElements/BP_Base_Flag_Supporter.BP_Base_Flag_Supporter_C"

        elements_to_insert.append((
            flagpole_id, new_base_id, center_x, center_y, base_z + 150,
            0, 0, 0, 1, 1, 1,
            flagpole_asset, 1, user_id, 1, user_id
        ))

        # 批量插入元素
        cursor.executemany("""
                           INSERT INTO base_element (element_id, base_id, location_x, location_y, location_z,
                                                     rotation_pitch, rotation_yaw, rotation_roll,
                                                     scale_x, scale_y, scale_z, asset,
                                                     element_health, owner_profile_id, quality, creator_prisoner_id)
                           VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                           """, elements_to_insert)

        conn.commit()
        print(f"[成功] 已创建基地 #{new_base_id}，包含 {len(elements_to_insert) - 1} 个建筑元素和 1 个旗帜支撑物")

    except sqlite3.Error as sqle:
        print(f"SQLite 错误: {sqle}")
        if conn:
            conn.rollback()
    except ValueError as ve:
        print(f"输入错误: {ve}")
    except Exception as e:
        print(f"发生错误: {e}")
    finally:
        if conn:
            conn.close()

# 执行元素生成
generate_elements()