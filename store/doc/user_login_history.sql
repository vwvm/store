create table user_login_history
(
    ID         int auto_increment comment 'ID'
        primary key,
    AREA       varchar(32) null comment '地区',
    COUNTRY    varchar(32) null comment '国家',
    USER_ID    varchar(32) null comment '用户id',
    IP         varchar(32) not null comment 'IP',
    LOGIN_TIME varchar(32) not null comment '时间'
)
    comment '登录历史表 ' charset = utf8mb3;

