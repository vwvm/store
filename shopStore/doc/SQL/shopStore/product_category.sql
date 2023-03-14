create table if not exists product_category
(
    id                  int auto_increment comment '序号',
    `category_id`       int(11)     NOT NULL COMMENT '主键 分类id主键',
    `category_name`     varchar(32) NOT NULL COMMENT '分类名称 分类名称',
    `category_level`    int(11)     NOT NULL COMMENT '分类层级 分类得类型，\n1:一级大分类\n2:二级分类\n3:三级小分类',
    `parent_id`         int(11)     NOT NULL COMMENT '父层级id 父id 上一级依赖的id，1级分类则为0，二级三级分别依赖上一级',
    `category_icon`     varchar(64) NULL DEFAULT NULL COMMENT '图标 logo',
    `category_slogan`   varchar(64) NULL DEFAULT NULL COMMENT '口号',
    `category_pic`      varchar(64) NULL DEFAULT NULL COMMENT '分类图',
    `category_bg_color` varchar(32) NULL DEFAULT NULL COMMENT '背景颜色',
    creation_time       datetime         default current_timestamp null comment '创建时间',
    creation_user_id    int         null comment '创建人id',
    update_time         datetime         default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id      int         null comment '更新人id',
    delete_flag         boolean          default false null comment '删除标记',
    constraint index_img_pk primary key (id)
) comment '商品分类信息';

INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)

VALUES (1, '点心/蛋糕', 1, 0, 'cake.png', '每一道甜品都有一个故事', 'act1.png', 'black');
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (2, '饼干/膨化', 1, 0, 'cookies.png', '健康饮食远离我', 'act2.png', 'black');
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (3, '熟食/肉类', 1, 0, 'meat.png', 'nice！nice！nice！', 'act3.png', 'black');
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (4, '素食/卤味', 1, 0, 'bamboo.png', NULL, 'act1.png', 'black');
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (5, '坚果/炒货', 1, 0, 'nut.png', '酥酥脆脆，回味无穷', 'act2.png', 'black');
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (6, '糖果/蜜饯', 1, 0, 'candy.png', NULL, 'act3.png', 'black');
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (7, '巧克力', 1, 0, 'chocolate.png', NULL, 'act1.png', 'black');
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (8, '海味/河鲜', 1, 0, 'fish.png', NULL, 'act2.png', 'black');
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (9, '花茶/果茶', 1, 0, 'tea.png', '健康快乐每一天', 'act3.png', 'black');
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (10, '品牌/礼包', 1, 0, 'package.png', '每日一个小惊喜', 'act1.png', 'black');
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (11, '蛋糕', 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (12, '点心', 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (13, '饼干', 2, 2, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (14, '薯片', 2, 2, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (15, '虾条', 2, 2, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (16, '猪肉脯', 2, 3, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (17, '牛肉丝', 2, 3, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (18, '小香肠', 2, 3, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (19, '豆干', 2, 4, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (20, '干笋', 2, 4, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (21, '鸭脖', 2, 4, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (22, '坚果', 2, 5, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (23, '锅巴', 2, 5, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (24, '糖果', 2, 6, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (25, '蜜饯', 2, 6, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (26, '巧克力', 2, 7, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (27, '果冻', 2, 7, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (28, '海带丝', 2, 8, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (29, '小鱼干', 2, 8, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (30, '鱿鱼丝', 2, 8, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (31, '花茶', 2, 9, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (32, '果茶', 2, 9, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (33, '品牌套餐', 2, 10, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (34, '活动礼包', 2, 10, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (35, '蒸蛋糕', 3, 11, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (36, '脱水蛋糕', 3, 11, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (37, '瑞士卷', 3, 11, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (38, '软面包', 3, 11, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (39, '马卡龙', 3, 11, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (40, '千层饼', 3, 11, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (41, '甜甜圈', 3, 11, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (42, '蒸三明治', 3, 11, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (43, '铜锣烧', 3, 11, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (44, '华夫饼', 3, 12, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (45, '老婆饼', 3, 12, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (46, '奥利奥', 3, 13, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (47, '粤利粤', 3, 13, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (49, '膨化虾条', 3, 15, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (50, '油炸虾条', 3, 15, NULL, NULL, NULL, NULL);
INSERT INTO `product_category`(category_id, category_name, category_level, parent_id, category_icon, category_slogan,
                               category_pic, category_bg_color)
VALUES (51, '海鲜虾条', 3, 15, NULL, NULL, NULL, NULL);

