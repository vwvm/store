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

