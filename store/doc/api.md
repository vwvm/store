# 商城后端接口说明

## 用户管理

### 用户登录

- 接口说明 ：接收账号和密码进行校验，返回校验结果
- 请求URL：/users/login
- 请求方式：get
- 请求参数

| key      | 类型     | 是否必须   | 说明            |
|----------|--------|--------|---------------|
| username | string | 是      | 用户登录账号        |
| password | string | 否      | 用户登录密码，默认1111 |

- 响应结果
  - `code` 响应状态码 10000表示成功，10001表示失败
  - `msg`  响应提示 提示信息
  - `data` 响应数据 如果登录成功响应user信息，失败响应null

### 用户注册

## 商品管理

### 添加商品

### 删除商品

### 修改商品