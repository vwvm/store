create table product_params
(
    param_id         varchar(64) not null comment '商品参数id'
        primary key,
    product_id       varchar(32) not null comment '商品id',
    product_place    varchar(32) not null comment '产地 产地，例：中国江苏',
    foot_period      varchar(32) not null comment '保质期 保质期，例：180天',
    brand            varchar(32) not null comment '品牌名 品牌名，例：三只大灰狼',
    factory_name     varchar(32) not null comment '生产厂名 生产厂名，例：大灰狼工厂',
    factory_address  varchar(32) not null comment '生产厂址 生产厂址，例：大灰狼生产基地',
    packaging_method varchar(32) not null comment '包装方式 包装方式，例：袋装',
    weight           varchar(32) not null comment '规格重量 规格重量，例：35g',
    storage_method   varchar(32) not null comment '存储方法 存储方法，例：常温5~25°',
    eat_method       varchar(32) not null comment '食用方式 食用方式，例：开袋即食',
    create_time      datetime    not null comment '创建时间',
    update_time      datetime    not null comment '更新时间'
)
    comment '商品参数 ' charset = utf8mb3;

