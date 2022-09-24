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

