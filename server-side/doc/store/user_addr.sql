create table user_addr
(
    addr_id         varchar(64)  not null comment '地址主键id'
        primary key,
    user_id         varchar(64)  not null comment '用户ID',
    receiver_name   varchar(32)  not null comment '收件人姓名',
    receiver_mobile varchar(32)  not null comment '收件人手机号',
    province        varchar(32)  not null comment '省份',
    city            varchar(32)  not null comment '城市',
    area            varchar(32)  not null comment '区县',
    addr            varchar(128) not null comment '详细地址',
    post_code       varchar(32)  null comment '邮编',
    status          int          null comment '状态,1正常，0无效',
    common_addr     int          null comment '是否默认地址 1是 1:是  0:否',
    create_time     datetime     not null comment '创建时间',
    update_time     datetime     not null comment '更新时间'
)
    comment '用户地址 ' charset = utf8mb3;

