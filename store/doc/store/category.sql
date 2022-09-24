create table category
(
    category_id       int auto_increment comment '主键 分类id主键'
        primary key,
    category_name     varchar(32) not null comment '分类名称 分类名称',
    category_level    int         not null comment '分类层级 分类得类型，
1:一级大分类
2:二级分类
3:三级小分类',
    parent_id         int         not null comment '父层级id 父id 上一级依赖的id，1级分类则为0，二级三级分别依赖上一级',
    category_icon     varchar(64) null comment '图标 logo',
    category_slogan   varchar(64) null comment '口号',
    category_pic      varchar(64) null comment '分类图',
    category_bg_color varchar(32) null comment '背景颜色'
)
    comment '商品分类' charset = utf8mb3;

