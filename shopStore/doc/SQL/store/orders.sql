create table orders
(
    order_id         varchar(64)                       not null comment '订单ID 同时也是订单编号'
        primary key,
    user_id          varchar(64)                       not null comment '用户ID',
    untitled         varchar(32)                       null comment '产品名称（多个产品用,隔开）',
    receiver_name    varchar(32)                       not null comment '收货人快照',
    receiver_mobile  varchar(32)                       not null comment '收货人手机号快照',
    receiver_address varchar(128)                      not null comment '收货地址快照',
    total_amount     decimal(32, 8)                    null comment '订单总价格',
    actual_amount    int                               null comment '实际支付总价格',
    pay_type         int                               null comment '支付方式 1:微信 2:支付宝',
    order_remark     varchar(32)                       null comment '订单备注',
    status           varchar(32)                       null comment '订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:已完成 6:已关闭',
    delivery_type    varchar(32)                       null comment '配送方式',
    delivery_flow_id varchar(32)                       null comment '物流单号',
    order_freight    decimal(32, 8) default 0.00000000 null comment '订单运费 默认可以为零，代表包邮',
    delete_status    int            default 0          null comment '逻辑删除状态 1: 删除 0:未删除',
    create_time      datetime                          null comment '创建时间（成交时间）',
    update_time      datetime                          null comment '更新时间',
    pay_time         datetime                          null comment '付款时间',
    delivery_time    datetime                          null comment '发货时间',
    flish_time       datetime                          null comment '完成时间',
    cancel_time      datetime                          null comment '取消时间',
    close_type       int                               null comment '订单关闭类型1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易'
)
    comment '订单 ' charset = utf8mb3;

