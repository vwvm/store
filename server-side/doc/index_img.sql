create table index_img
(
    img_id       varchar(64)  not null comment '主键'
        primary key,
    img_url      varchar(128) not null comment '图片 图片地址',
    img_bg_color varchar(32)  null comment '背景色 背景颜色',
    prod_id      varchar(64)  null comment '商品id 商品id',
    category_id  varchar(64)  null comment '商品分类id 商品分类id',
    index_type   int          not null comment '轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类',
    seq          int          not null comment '轮播图展示顺序 轮播图展示顺序，从小到大',
    status       int          not null comment '是否展示:1表示正常显示，0表示下线 是否展示，1：展示    0：不展示',
    create_time  datetime     not null comment '创建时间 创建时间',
    update_time  datetime     not null comment '更新时间 更新'
)
    comment '轮播图 ' charset = utf8mb3;

INSERT INTO store.index_img (img_id, img_url, img_bg_color, prod_id, category_id, index_type, seq, status, create_time, update_time) VALUES ('1', 'ad1.jpg', 'green', '101', null, 1, 1, 1, '2021-04-14 17:21:13', '2021-04-14 17:21:18');
INSERT INTO store.index_img (img_id, img_url, img_bg_color, prod_id, category_id, index_type, seq, status, create_time, update_time) VALUES ('2', 'ad2.jpg', 'pink', null, '1', 2, 2, 1, '2021-04-14 17:21:49', '2021-04-14 17:21:52');
INSERT INTO store.index_img (img_id, img_url, img_bg_color, prod_id, category_id, index_type, seq, status, create_time, update_time) VALUES ('3', 'ad3.jpg', 'black', '103', null, 1, 3, 1, '2021-04-14 17:22:23', '2021-04-14 17:22:27');
INSERT INTO store.index_img (img_id, img_url, img_bg_color, prod_id, category_id, index_type, seq, status, create_time, update_time) VALUES ('4', 'ad4.jpg', 'orange', null, '2', 2, 4, 1, '2021-04-14 17:23:06', '2021-04-14 17:23:08');
INSERT INTO store.index_img (img_id, img_url, img_bg_color, prod_id, category_id, index_type, seq, status, create_time, update_time) VALUES ('5', 'ad5.jpg', 'yellow', '101', null, 1, 2, 0, '2021-04-24 09:34:01', '2021-04-25 09:34:05');
