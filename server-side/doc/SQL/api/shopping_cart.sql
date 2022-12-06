create table shopping_cart
(
    cart_id       int auto_increment comment '主键'
        primary key,
    product_id    varchar(32)    not null comment '商品ID',
    sku_id        varchar(32)    not null comment 'skuID',
    user_id       varchar(32)    not null comment '用户ID',
    cart_num      varchar(32)    not null comment '购物车商品数量',
    cart_time     varchar(32)    not null comment '添加购物车时间',
    product_price decimal(32, 8) null comment '添加购物车时商品价格',
    sku_props     varchar(255)   null comment '选择的套餐的属性'
)
    comment '购物车 ' charset = utf8mb3;

INSERT INTO store.shopping_cart (cart_id, product_id, sku_id, user_id, cart_num, cart_time, product_price, sku_props) VALUES (5, '3', '1', '6', '1', '2021-05-12 09:18:35', 1.00000000, '口味:原味;包装:盒装;');
INSERT INTO store.shopping_cart (cart_id, product_id, sku_id, user_id, cart_num, cart_time, product_price, sku_props) VALUES (6, '1', '4', '6', '1', '2021-05-12 09:18:47', 8.00000000, '口味:草莓;');
INSERT INTO store.shopping_cart (cart_id, product_id, sku_id, user_id, cart_num, cart_time, product_price, sku_props) VALUES (7, '3', '1', '1', '1', '2021-05-12 09:37:15', 1.00000000, '口味:原味;包装:盒装;');
