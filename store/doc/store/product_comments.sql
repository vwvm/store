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

