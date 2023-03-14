create table product
(
    product_id       varchar(64) not null comment '商品主键id'
        primary key,
    product_name     varchar(32) not null comment '商品名称 商品名称',
    category_id      int         not null comment '分类外键id 分类id',
    root_category_id int         not null comment '一级分类外键id 一级分类id，用于优化查询',
    sold_num         int         not null comment '销量 累计销售',
    product_status   int         not null comment '默认是1，表示正常状态, -1表示删除, 0下架 默认是1，表示正常状态, -1表示删除, 0下架',
    content          text        not null comment '商品内容 商品内容',
    create_time      datetime    not null comment '创建时间',
    update_time      datetime    not null comment '更新时间'
)
    comment '商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表' charset = utf8mb3;

