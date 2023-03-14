create table if not exists product_specification
(
    id                           int auto_increment comment '序号',
    `product_specification_id`   varchar(64)   NOT NULL COMMENT '商品规格编号',
    `product_id`                 varchar(64)   NOT NULL COMMENT '商品id',
    `product_specification_name` varchar(32)   NOT NULL COMMENT '商品规格名称',
    `product_specification_img`  varchar(32)   NULL DEFAULT NULL COMMENT '商品规格图片',
    `untitled`                   json          NULL DEFAULT NULL COMMENT '属性组合（格式是p1:[v1];p2:[v2]）',
    `original_price`             int(11)       NOT NULL COMMENT '原价',
    `sell_price`                 int(11)       NOT NULL COMMENT '销售价格',
    `discounts`                  decimal(4, 2) NOT NULL COMMENT '折扣力度',
    `stock`                      int(11)       NOT NULL COMMENT '库存',
    `status`                     int(11)       NULL default NULL COMMENT '商品规格状态(1启用，0禁用，-1删除)',
    creation_time                datetime           default current_timestamp null comment '创建时间',
    creation_user_id             int           null comment '创建人id',
    update_time                  datetime           default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id               int           null comment '更新人id',
    delete_flag                  boolean            default false null comment '删除标记',
    constraint index_img_pk primary key (id)
) COMMENT = '商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计';
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('1', '3', '咪咪虾条 单包 50g', 'mmxt_1.png', '{
  "口味": [
    \"原味\",
    \"烧烤\",
    \"芥末\"
  ],
  "包装": [
    \"盒装\",
    \"袋装\"
  ]
}', 2, 1, 0.70, 316, '2021-04-26 15:58:00', '2021-04-25 15:58:00', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('10', '24', '加力加 100g', 'jlj_1.png', '{
  "口味": [
    \"原味\",
    \"微辣\",
    \"BT\"
  ]
}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('11', '25', '加力加 100g', 'jlj_1.png', '{
  "口味": [
    \"原味\",
    \"微辣\",
    \"BT\"
  ]
}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('12', '26', '加力加 100g', 'jlj_1.png', '{
  "口味": [
    \"原味\",
    \"微辣\",
    \"BT\"
  ]
}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('13', '27', '加力加 100g', 'jlj_1.png', '{
  "口味": [
    \"原味\",
    \"微辣\",
    \"BT\"
  ]
}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('14', '28', '加力加 100g', 'jlj_1.png', '{
  "口味": [
    \"原味\",
    \"微辣\",
    \"BT\"
  ]
}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('15', '29', '加力加 100g', 'jlj_1.png', '{
  "口味": [
    \"原味\",
    \"微辣\",
    \"BT\"
  ]
}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('2', '3', '咪咪虾条 6连包', 'mmxt_1.png', '{
  "口味": [
    \"原味\",
    \"烧烤\"
  ],
  "包装": [
    \"新包装\",
    \"老包装\"
  ]
}', 6, 5, 0.60, 171, '2021-04-26 14:48:21', '2021-04-26 14:48:25', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('3', '3', '咪咪虾条 整箱50包', 'mmxt_1.png', '{
  "口味": [
    \"原味\",
    \"烧烤\",
    \"孜然\"
  ]
}', 50, 40, 0.50, 37, '2021-04-26 14:49:20', '2021-04-26 14:49:23', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('4', '1', '奥利奥分享装', 'ala_1.png', '{
  "口味": [
    \"草莓\",
    \"巧克力\",
    \"牛奶\",
    \"原味\"
  ]
}', 10, 8, 0.80, 137, '2021-04-27 11:38:55', '2021-04-27 11:39:00', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('5', '19', '加力加 100g', 'jlj_1.png', '{
  "口味": [
    \"原味\",
    \"微辣\",
    \"BT\"
  ]
}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('6', '20', '加力加 100g', 'jlj_1.png', '{
  "口味": [
    \"原味\",
    \"微辣\",
    \"BT\"
  ]
}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('7', '21', '加力加 100g', 'jlj_1.png', '{
  "口味": [
    \"原味\",
    \"微辣\",
    \"BT\"
  ]
}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('8', '22', '加力加 100g', 'jlj_1.png', '{
  "口味": [
    \"原味\",
    \"微辣\",
    \"BT\"
  ]
}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('9', '23', '加力加 100g', 'jlj_1.png', '{
  "口味": [
    \"原味\",
    \"微辣\",
    \"BT\"
  ]
}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);
INSERT INTO product_specification
(product_specification_id, product_id, product_specification_name, product_specification_img, untitled, original_price,
 sell_price, discounts, stock, creation_time, update_time, status)
VALUES ('9', '131', '加力加 100g', 'jlj_1.png', '{
  "口味": [
    \"原味\",
    \"微辣\",
    \"BT\"
  ]
}', 3, 2, 0.50, 999, '2021-05-10 09:56:09', '2021-05-10 09:56:12', 1);