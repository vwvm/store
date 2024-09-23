# -*- coding: utf-8 -*-

from sklearn import datasets
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score

# 加载数据集
iris = datasets.load_iris()
x = iris.data
y = iris.target

# 将数据集拆分为训练集和测试集
train_x, test_x, train_y, test_y = train_test_split(x, y, test_size=0.2)

# 创建逻辑回归对象
lr = LogisticRegression(max_iter=10000)

# 使用训练集训练模型
lr.fit(train_x, train_y)

# 对测试集进行预测
y_pred = lr.predict(test_x)

# 打印模型的准确率
accuracy = accuracy_score(test_y, y_pred)
print("准确率：%.2f" % accuracy)
 