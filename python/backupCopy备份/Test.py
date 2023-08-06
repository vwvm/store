import sqlite3

# 连接到数据库（如果不存在则会创建）
conn = sqlite3.connect('mydatabase.db')

# 创建一个游标对象
cursor = conn.cursor()

# 创建一个名为 "books" 的表
cursor.execute('''
    CREATE TABLE IF NOT EXISTS books (
        id INTEGER PRIMARY KEY,
        title TEXT,
        author TEXT
    )
''')

# 插入数据
cursor.execute("INSERT INTO books (title, author) VALUES (?, ?)", ("Sample Book", "John Doe"))
# 查询数据
cursor.execute("SELECT * FROM books")
rows = cursor.fetchall()

# 输出查询结果
for row in rows:
    print(row)

cursor.execute("INSERT INTO books (title, author) VALUES (?, ?)", ("Sample Book", "John Doe"))


# 提交更改并关闭连接
conn.commit()
conn.close()

# 重新连接数据库
conn = sqlite3.connect('mydatabase.db')
cursor = conn.cursor()

# 查询数据
cursor.execute("SELECT * FROM books")
rows = cursor.fetchall()

# 输出查询结果
for row in rows:
    print(row)

# 关闭连接
conn.close()
