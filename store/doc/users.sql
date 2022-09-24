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

INSERT INTO store.users (user_id, username, password, nickname, realname, user_img, user_mobile, user_email, user_sex, user_birth, user_regtime, user_modtime) VALUES (1, 'zhangsan', 'f379eaf3c831b04de153469d1bec345e', null, null, 'img/default.png', null, null, null, null, '2021-04-15 16:10:53', '2021-04-15 16:10:53');
INSERT INTO store.users (user_id, username, password, nickname, realname, user_img, user_mobile, user_email, user_sex, user_birth, user_regtime, user_modtime) VALUES (2, 'aaaa', '1111', null, null, 'img/default.png', null, null, null, null, '2021-04-16 11:10:01', '2021-04-16 11:10:01');
INSERT INTO store.users (user_id, username, password, nickname, realname, user_img, user_mobile, user_email, user_sex, user_birth, user_regtime, user_modtime) VALUES (3, 'wahaha', '4297f44b13955235245b2497399d7a93', null, null, 'img/default.png', null, null, null, null, '2021-04-16 15:36:52', '2021-04-16 15:36:52');
INSERT INTO store.users (user_id, username, password, nickname, realname, user_img, user_mobile, user_email, user_sex, user_birth, user_regtime, user_modtime) VALUES (4, 'heihei', '4297f44b13955235245b2497399d7a93', '二狗', null, 'img/default.png', null, null, null, null, '2021-04-16 15:38:49', '2021-04-16 15:38:49');
INSERT INTO store.users (user_id, username, password, nickname, realname, user_img, user_mobile, user_email, user_sex, user_birth, user_regtime, user_modtime) VALUES (5, 'Tony', '4297f44b13955235245b2497399d7a93', '托尼', null, 'img/default.png', null, null, null, null, '2021-04-23 10:25:04', '2021-04-23 10:25:04');
INSERT INTO store.users (user_id, username, password, nickname, realname, user_img, user_mobile, user_email, user_sex, user_birth, user_regtime, user_modtime) VALUES (6, 'hanmeimei', 'f379eaf3c831b04de153469d1bec345e', null, null, 'img/default.png', null, null, null, null, '2021-04-23 10:30:52', '2021-04-23 10:30:52');
INSERT INTO store.users (user_id, username, password, nickname, realname, user_img, user_mobile, user_email, user_sex, user_birth, user_regtime, user_modtime) VALUES (7, 'lilei123', '4297f44b13955235245b2497399d7a93', null, null, 'img/default.png', null, null, null, null, '2021-04-23 10:33:03', '2021-04-23 10:33:03');
INSERT INTO store.users (user_id, username, password, nickname, realname, user_img, user_mobile, user_email, user_sex, user_birth, user_regtime, user_modtime) VALUES (8, 'tom123123', '4297f44b13955235245b2497399d7a93', null, null, 'img/default.png', null, null, null, null, '2021-04-23 10:34:49', '2021-04-23 10:34:49');
INSERT INTO store.users (user_id, username, password, nickname, realname, user_img, user_mobile, user_email, user_sex, user_birth, user_regtime, user_modtime) VALUES (9, 'admin123', '192023a7bbd73250516f069df18b500', null, null, 'img/default.png', null, null, null, null, '2022-09-02 16:18:18', '2022-09-02 16:18:18');
