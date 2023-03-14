create table users
(
    user_id      int auto_increment comment '主键id 用户id'
        primary key,
    username     varchar(32)   not null comment '用户名 用户名',
    password     varchar(64)   not null comment '密码 密码',
    nickname     varchar(32)   null comment '昵称 昵称',
    realname     varchar(128)  null comment '真实姓名 真实姓名',
    user_img     varchar(1024) not null comment '头像 头像',
    user_mobile  varchar(32)   null comment '手机号 手机号',
    user_email   varchar(32)   null comment '邮箱地址 邮箱地址',
    user_sex     char          null comment '性别 M(男) or F(女)',
    user_birth   date          null comment '生日 生日',
    user_regtime datetime      not null comment '注册时间 创建时间',
    user_modtime datetime      not null comment '更新时间 更新时间'
)
    comment '用户 ' charset = utf8mb3;

