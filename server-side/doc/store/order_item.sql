create table order_item
(
    item_id       varchar(64)    not null comment '订单项ID'
        primary key,
    order_id      varchar(64)    not null comment '订单ID',
    product_id    varchar(64)    not null comment '商品ID',
    product_name  varchar(32)    not null comment '商品名称',
    product_img   varchar(32)    null comment '商品图片',
    sku_id        varchar(128)   not null comment 'skuID',
    sku_name      varchar(32)    not null comment 'sku名称',
    product_price decimal(32, 8) not null comment '商品价格',
    buy_counts    int            not null comment '购买数量',
    total_amount  decimal(32, 8) null comment '商品总金额',
    basket_date   datetime       null comment '加入购物车时间',
    buy_time      datetime       null comment '购买时间',
    is_comment    int            null comment '评论状态： 0 未评价  1 已评价'
)
    comment '订单项/快照 ' charset = utf8mb3;

