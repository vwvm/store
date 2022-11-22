create table product_img
(
    id           varchar(64)  not null comment '图片主键'
        primary key,
    item_id      varchar(64)  not null comment '商品外键id 商品外键id',
    url          varchar(128) not null comment '图片地址 图片地址',
    sort         int          not null comment '顺序 图片顺序，从小到大',
    is_main      int          not null comment '是否主图 是否主图，1：是，0：否',
    created_time datetime     not null comment '创建时间',
    updated_time datetime     not null comment '更新时间'
)
    comment '商品图片 ' charset = utf8mb3;

INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('1', '1', 'ala_1.png', 1, 1, '2021-04-22 15:25:08', '2021-04-22 15:25:15');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('10', '6', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('11', '7', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('12', '8', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('13', '9', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('14', '10', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('15', '11', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('16', '12', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('17', '13', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('18', '14', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('19', '15', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('2', '1', 'ala_2.png', 2, 0, '2021-04-22 15:25:39', '2021-04-22 15:25:44');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('20', '16', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('21', '17', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('22', '18', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('23', '19', 'jlj_1.png', 1, 1, '2021-05-10 09:54:10', '2021-05-10 09:54:12');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('3', '2', 'hfb_1.png', 1, 1, '2021-04-24 15:30:09', '2021-04-25 15:30:19');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('4', '2', 'hfb_2.png', 2, 0, '2021-04-25 15:30:45', '2021-04-25 15:30:48');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('5', '3', 'mmxt_1.png', 1, 1, '2021-04-25 15:59:28', '2021-04-25 15:59:31');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('6', '3', 'mmxt_2.png', 2, 0, '2021-04-25 15:59:51', '2021-04-25 15:59:54');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('7', '4', 'wwxb_1.png', 1, 1, '2021-04-25 16:00:12', '2021-04-25 16:00:17');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('8', '4', 'wwxb_2.png', 2, 0, '2021-04-25 16:00:36', '2021-04-25 16:00:39');
INSERT INTO store.product_img (id, item_id, url, sort, is_main, created_time, updated_time) VALUES ('9', '5', 'wwxb_1.png', 1, 1, '2021-04-26 11:11:11', '2021-04-26 11:11:11');
