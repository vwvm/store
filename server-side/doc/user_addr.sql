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

INSERT INTO store.user_addr (addr_id, user_id, receiver_name, receiver_mobile, province, city, area, addr, post_code, status, common_addr, create_time, update_time) VALUES ('1', '1', '张三', '13030303300', '湖北', '武汉', '江夏区', '光谷大道77号', '421000', 1, 1, '2021-04-30 09:33:30', '2021-04-28 09:33:37');
INSERT INTO store.user_addr (addr_id, user_id, receiver_name, receiver_mobile, province, city, area, addr, post_code, status, common_addr, create_time, update_time) VALUES ('2', '1', '小丽', '13131313311', '湖北', '宜昌', '夷陵区', '巴拉巴拉', '410000', 1, 0, '2021-04-30 09:34:41', '2021-04-30 09:34:44');
INSERT INTO store.user_addr (addr_id, user_id, receiver_name, receiver_mobile, province, city, area, addr, post_code, status, common_addr, create_time, update_time) VALUES ('3', '6', '韩梅梅', '13232323322', '湖南', '长沙', '岳麓区', '淅沥淅沥', '430000', 1, 1, '2021-04-30 09:35:49', '2021-04-30 09:35:53');
