CREATE TABLE `users`
(
    `user_id`      int(64)       NOT NULL AUTO_INCREMENT COMMENT '主键id 用户id',
    `username`     varchar(32)   NOT NULL COMMENT '用户名 用户名',
    `password`     varchar(64)   NOT NULL COMMENT '密码 密码',
    `nickname`     varchar(32)   NULL DEFAULT NULL COMMENT '昵称 昵称',
    `realname`     varchar(128)  NULL DEFAULT NULL COMMENT '真实姓名 真实姓名',
    `user_img`     varchar(1024) NOT NULL COMMENT '头像 头像',
    `user_mobile`  varchar(32)   NULL DEFAULT NULL COMMENT '手机号 手机号',
    `user_email`   varchar(32)   NULL DEFAULT NULL COMMENT '邮箱地址 邮箱地址',
    `user_sex`     char(1)       NULL DEFAULT NULL COMMENT '性别 M(男) or F(女)',
    `user_birth`   date          NULL DEFAULT NULL COMMENT '生日 生日',
    `user_regtime` datetime           default current_timestamp NOT NULL COMMENT '注册时间 创建时间',
    `user_modtime` datetime           default current_timestamp null on update current_timestamp comment '更新时间',
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户 '
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users`
VALUES (1, 'zhangsan', 'f379eaf3c831b04de153469d1bec345e', NULL, NULL, 'img/default.png', NULL, NULL, NULL, NULL,
        '2021-04-15 16:10:53', '2021-04-15 16:10:53');
INSERT INTO `users`
VALUES (2, 'aaaa', '1111', NULL, NULL, 'img/default.png', NULL, NULL, NULL, NULL, '2021-04-16 11:10:01',
        '2021-04-16 11:10:01');
INSERT INTO `users`
VALUES (3, 'wahaha', '4297f44b13955235245b2497399d7a93', NULL, NULL, 'img/default.png', NULL, NULL, NULL, NULL,
        '2021-04-16 15:36:52', '2021-04-16 15:36:52');
INSERT INTO `users`
VALUES (4, 'heihei', '4297f44b13955235245b2497399d7a93', '二狗', NULL, 'img/default.png', NULL, NULL, NULL, NULL,
        '2021-04-16 15:38:49', '2021-04-16 15:38:49');
INSERT INTO `users`
VALUES (5, 'Tony', '4297f44b13955235245b2497399d7a93', '托尼', NULL, 'img/default.png', NULL, NULL, NULL, NULL,
        '2021-04-23 10:25:04', '2021-04-23 10:25:04');
INSERT INTO `users`
VALUES (6, 'hanmeimei', 'f379eaf3c831b04de153469d1bec345e', NULL, NULL, 'img/default.png', NULL, NULL, NULL, NULL,
        '2021-04-23 10:30:52', '2021-04-23 10:30:52');
INSERT INTO `users`
VALUES (7, 'lilei123', '4297f44b13955235245b2497399d7a93', NULL, NULL, 'img/default.png', NULL, NULL, NULL, NULL,
        '2021-04-23 10:33:03', '2021-04-23 10:33:03');
INSERT INTO `users`
VALUES (8, 'tom123123', '4297f44b13955235245b2497399d7a93', NULL, NULL, 'img/default.png', NULL, NULL, NULL, NULL,
        '2021-04-23 10:34:49', '2021-04-23 10:34:49');
INSERT INTO `users`
VALUES (9, 'admin', 'c3284d0f94606de1fd2af172aba15bf3', NULL, NULL, 'img/default.png', NULL, NULL, NULL, NULL,
        '2021-04-23 10:34:49', '2021-04-23 10:34:49');
