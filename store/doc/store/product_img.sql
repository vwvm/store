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

