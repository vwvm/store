create database if not exists shopStore;

create table if not exists index_img
(
    id               int auto_increment comment '序号',
    img_url          varchar(128)                       null comment '图片地址',
    img_bg_color     varchar(32)                        null comment '背景颜色',
    product_id       varchar(64)                        null comment '商品id',
    category_id      varchar(64)                        null comment '商品分类id',
    seq              int                                null comment '轮播图展示顺序',
    index_type       int                                null comment '轮播图类型',
    status           int                                null comment '是否展示',
    creation_time    datetime default current_timestamp null comment '创建时间',
    creation_user_id int                                null comment '创建人id',
    update_time      datetime default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id   int                                null comment '更新人id',
    delete_flag      boolean  default false             null comment '删除标记',
    constraint index_img_pk primary key (id)
) comment '轮播图';

INSERT INTO shopStore.index_img (img_url, img_bg_color, product_id, category_id, index_type, seq, status, creation_time,
                                 update_time)
VALUES ('/one/bg1.png', 'green', '101', null, 1, 1, 1, '2021-04-14 17:21:13', '2021-04-14 17:21:18');
INSERT INTO shopStore.index_img (img_url, img_bg_color, product_id, category_id, index_type, seq, status, creation_time,
                                 update_time)
VALUES ('/one/bg2.jpg', 'pink', null, '1', 2, 2, 1, '2021-04-14 17:21:49', '2021-04-14 17:21:52');
INSERT INTO shopStore.index_img (img_url, img_bg_color, product_id, category_id, index_type, seq, status, creation_time,
                                 update_time)
VALUES ('/one/bg3.jpg', 'black', '103', null, 1, 3, 1, '2021-04-14 17:22:23', '2021-04-14 17:22:27');
INSERT INTO shopStore.index_img (img_url, img_bg_color, product_id, category_id, index_type, seq, status, creation_time,
                                 update_time)
VALUES ('/one/bg4.png', 'orange', null, '2', 2, 4, 1, '2021-04-14 17:23:06', '2021-04-14 17:23:08');
INSERT INTO shopStore.index_img (img_url, img_bg_color, product_id, category_id, index_type, seq, status, creation_time,
                                 update_time)
VALUES ('/one/bg5.png', 'yellow', '101', null, 1, 2, 0, '2021-04-24 09:34:01', '2021-04-25 09:34:05');

create table if not exists product_params
(
    id                 int auto_increment comment '序号',
    `param_id`         varchar(64)                        NULL COMMENT '商品参数id',
    `product_id`       varchar(32)                        NULL COMMENT '商品id',
    `product_place`    varchar(32)                        NULL COMMENT '产地 产地，例：中国江苏',
    `foot_period`      varchar(32)                        NULL COMMENT '保质期 保质期，例：180天',
    `brand`            varchar(32)                        NULL COMMENT '品牌名 品牌名，例：三只大灰狼',
    `factory_name`     varchar(32)                        NULL COMMENT '生产厂名 生产厂名，例：大灰狼工厂',
    `factory_address`  varchar(32)                        NULL COMMENT '生产厂址 生产厂址，例：大灰狼生产基地',
    `packaging_method` varchar(32)                        NULL COMMENT '包装方式 包装方式，例：袋装',
    `weight`           varchar(32)                        NULL COMMENT '规格重量 规格重量，例：35g',
    `storage_method`   varchar(32)                        NULL COMMENT '存储方法 存储方法，例：常温5~25°',
    `eat_method`       varchar(32)                        NULL COMMENT '食用方式 食用方式，例：开袋即食',
    creation_time      datetime default current_timestamp null comment '创建时间',
    creation_user_id   int                                null comment '创建人id',
    update_time        datetime default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id     int                                null comment '更新人id',
    delete_flag        boolean  default false             null comment '删除标记',
    constraint index_img_pk primary key (id)
) comment '商品参数';




