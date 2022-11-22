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

INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('1', '奥利奥小饼干666', 46, 2, 196, 1, '<label style="color:red;font-size:18px">1.全新包装</label><br/><img src="static/pimgs/ala_desc1.png"/><br/><label style="color:green;font-size:18px">2.全新口味</label><br/><img src="static/pimgs/ala_desc2.png"/><label style="color:blue;font-size:18px">3.全新体验</label><br/><img src="static/pimgs/ala_desc3.png"/>', '2021-04-26 15:20:51', '2021-04-23 15:20:58');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('10', '商品10', 10, 1, 127, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('11', '商品11', 10, 1, 128, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('12', '商品12', 46, 2, 122, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('13', '商品13', 46, 2, 123, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('14', '商品14', 46, 2, 124, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('15', '商品15', 46, 2, 125, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('16', '商品16', 46, 2, 126, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('17', '商品17', 46, 2, 127, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('18', '商品18', 46, 2, 128, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('19', '加力加小虾条1', 49, 2, 111, 1, '加力加虾条说明', '2021-03-04 09:53:14', '2021-05-10 09:53:20');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('2', '旺旺华夫饼', 22, 1, 202, 1, '旺旺华夫饼商品内容', '2021-04-26 15:27:22', '2021-04-25 15:27:25');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('20', '加力加虾条2', 49, 2, 199, 1, '加力加虾条说明', '2021-03-04 09:53:14', '2021-05-10 09:53:20');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('21', '加力加虾条3', 49, 2, 168, 1, '加力加虾条说明', '2021-03-04 09:53:14', '2021-05-10 09:53:20');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('22', '加力加虾条4', 49, 2, 168, 1, '加力加虾条说明', '2021-03-04 09:53:14', '2021-05-10 09:53:20');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('23', '加力加虾条5', 49, 2, 168, 1, '加力加虾条说明', '2021-03-04 09:53:14', '2021-05-10 09:53:20');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('24', '加力加虾条6', 49, 2, 168, 1, '加力加虾条说明', '2021-03-04 09:53:14', '2021-05-10 09:53:20');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('25', '加力加虾条7', 49, 2, 168, 1, '加力加虾条说明', '2021-03-04 09:53:14', '2021-05-10 09:53:20');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('26', '加力加虾条8', 49, 2, 168, 1, '加力加虾条说明', '2021-03-04 09:53:14', '2021-05-10 09:53:20');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('27', '加力加虾条9', 49, 2, 168, 1, '加力加虾条说明', '2021-03-04 09:53:14', '2021-05-10 09:53:20');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('28', '加力加虾条10', 49, 2, 168, 1, '加力加虾条说明', '2021-03-04 09:53:14', '2021-05-10 09:53:20');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('29', '加力加虾条11', 49, 2, 168, 1, '加力加虾条说明', '2021-03-04 09:53:14', '2021-05-10 09:53:20');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('3', '咪咪小虾条', 50, 2, 199, 1, '<img src="static/pimgs/mmxt_desc.jpg"/>', '2021-04-26 15:58:09', '2021-04-25 15:58:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('4', '旺旺雪饼', 46, 2, 201, 1, '旺旺雪饼商品内容', '2021-04-25 15:58:59', '2021-04-25 15:59:03');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('5', '商品5', 10, 1, 122, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('6', '商品6', 10, 1, 123, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('7', '商品7', 10, 1, 124, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('8', '商品8', 10, 1, 125, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
INSERT INTO store.product (product_id, product_name, category_id, root_category_id, sold_num, product_status, content, create_time, update_time) VALUES ('9', '商品9', 10, 1, 126, 1, '商品说明', '2021-04-26 11:11:11', '2021-04-26 11:11:11');
