create table product_comments
(
    comm_id       varchar(64)  not null comment 'ID'
        primary key,
    product_id    varchar(64)  not null comment '商品id',
    product_name  varchar(64)  null comment '商品名称',
    order_item_id varchar(64)  null comment '订单项(商品快照)ID 可为空',
    user_id       varchar(64)  null comment '评论用户id 用户名须脱敏',
    is_anonymous  int          null comment '是否匿名（1:是，0:否）',
    comm_type     int          null comment '评价类型（1好评，0中评，-1差评）',
    comm_level    int          not null comment '评价等级 1：好评 2：中评 3：差评',
    comm_content  varchar(128) not null comment '评价内容',
    comm_imgs     varchar(32)  null comment '评价晒图(JSON {img1:url1,img2:url2}  )',
    sepc_name     datetime     null comment '评价时间 可为空',
    reply_status  int          null comment '是否回复（0:未回复，1:已回复）',
    reply_content varchar(32)  null comment '回复内容',
    reply_time    datetime     null comment '回复时间',
    is_show       int          null comment '是否显示（1:是，0:否）'
)
    comment '商品评价 ' charset = utf8mb3;

INSERT INTO store.product_comments (comm_id, product_id, product_name, order_item_id, user_id, is_anonymous, comm_type, comm_level, comm_content, comm_imgs, sepc_name, reply_status, reply_content, reply_time, is_show) VALUES ('1', '3', '咪咪虾条', '10000001', '4', 1, 1, 1, '我买这不是来吃的，就是玩儿', '{}', '2021-04-27 14:51:10', 0, null, '2021-04-27 14:51:32', 1);
INSERT INTO store.product_comments (comm_id, product_id, product_name, order_item_id, user_id, is_anonymous, comm_type, comm_level, comm_content, comm_imgs, sepc_name, reply_status, reply_content, reply_time, is_show) VALUES ('2', '3', '咪咪虾条', '10000002', '5', 0, 1, 1, 'nice', '{img1:“mmxt2.png”}', '2021-04-27 14:53:20', 1, '你好我也好', '2021-04-27 14:53:37', 1);
INSERT INTO store.product_comments (comm_id, product_id, product_name, order_item_id, user_id, is_anonymous, comm_type, comm_level, comm_content, comm_imgs, sepc_name, reply_status, reply_content, reply_time, is_show) VALUES ('3', '3', '咪咪虾条', '10000003', '1', 1, 1, 1, '评价内容3', '{}', '2021-04-27 09:36:36', 0, null, null, 1);
INSERT INTO store.product_comments (comm_id, product_id, product_name, order_item_id, user_id, is_anonymous, comm_type, comm_level, comm_content, comm_imgs, sepc_name, reply_status, reply_content, reply_time, is_show) VALUES ('4', '3', '咪咪虾条', '10000004', '2', 1, 1, 1, '评价内容3', '{}', '2021-04-28 09:37:28', 0, null, null, 1);
INSERT INTO store.product_comments (comm_id, product_id, product_name, order_item_id, user_id, is_anonymous, comm_type, comm_level, comm_content, comm_imgs, sepc_name, reply_status, reply_content, reply_time, is_show) VALUES ('5', '3', '咪咪虾条', '10000005', '2', 1, 0, 1, '评价内容5', '{}', '2021-04-28 09:38:13', 0, null, null, 1);
INSERT INTO store.product_comments (comm_id, product_id, product_name, order_item_id, user_id, is_anonymous, comm_type, comm_level, comm_content, comm_imgs, sepc_name, reply_status, reply_content, reply_time, is_show) VALUES ('6', '3', '咪咪虾条', '10000006', '3', 1, 0, 1, '评价内容6', '{}', '2021-04-21 09:39:16', 0, null, null, 1);
INSERT INTO store.product_comments (comm_id, product_id, product_name, order_item_id, user_id, is_anonymous, comm_type, comm_level, comm_content, comm_imgs, sepc_name, reply_status, reply_content, reply_time, is_show) VALUES ('7', '3', '咪咪虾条', '10000007', '4', 0, -1, 1, '评价内容7', '{}', '2021-04-06 09:40:01', 0, null, null, 1);
