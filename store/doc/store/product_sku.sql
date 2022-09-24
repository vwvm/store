create table product_sku
(
    sku_id         varchar(64)   not null comment 'sku编号'
        primary key,
    product_id     varchar(64)   not null comment '商品id',
    sku_name       varchar(32)   not null comment 'sku名称',
    sku_img        varchar(32)   null comment 'sku图片',
    untitled       varchar(400)  null comment '属性组合（格式是p1:v1;p2:v2）',
    original_price int           not null comment '原价',
    sell_price     int           not null comment '销售价格',
    discounts      decimal(4, 2) not null comment '折扣力度',
    stock          int           not null comment '库存',
    create_time    datetime      not null comment '创建时间',
    update_time    datetime      not null comment '更新时间',
    status         int           null comment 'sku状态(1启用，0禁用，-1删除)'
)
    comment '商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计' charset = utf8mb3;

