create table product_params
(
    param_id         varchar(64) not null comment '商品参数id'
        primary key,
    product_id       varchar(32) not null comment '商品id',
    product_place    varchar(32) not null comment '产地 产地，例：中国江苏',
    foot_period      varchar(32) not null comment '保质期 保质期，例：180天',
    brand            varchar(32) not null comment '品牌名 品牌名，例：三只大灰狼',
    factory_name     varchar(32) not null comment '生产厂名 生产厂名，例：大灰狼工厂',
    factory_address  varchar(32) not null comment '生产厂址 生产厂址，例：大灰狼生产基地',
    packaging_method varchar(32) not null comment '包装方式 包装方式，例：袋装',
    weight           varchar(32) not null comment '规格重量 规格重量，例：35g',
    storage_method   varchar(32) not null comment '存储方法 存储方法，例：常温5~25°',
    eat_method       varchar(32) not null comment '食用方式 食用方式，例：开袋即食',
    create_time      datetime    not null comment '创建时间',
    update_time      datetime    not null comment '更新时间'
)
    comment '商品参数 ' charset = utf8mb3;

INSERT INTO store.product_params (param_id, product_id, product_place, foot_period, brand, factory_name, factory_address, packaging_method, weight, storage_method, eat_method, create_time, update_time) VALUES ('1', '3', '中国湖北武汉', '12个月', '咪咪', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO store.product_params (param_id, product_id, product_place, foot_period, brand, factory_name, factory_address, packaging_method, weight, storage_method, eat_method, create_time, update_time) VALUES ('2', '19', '中国湖北武汉', '12个月', '卫龙', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO store.product_params (param_id, product_id, product_place, foot_period, brand, factory_name, factory_address, packaging_method, weight, storage_method, eat_method, create_time, update_time) VALUES ('3', '20', '中国湖北武汉', '12个月', '威龙', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO store.product_params (param_id, product_id, product_place, foot_period, brand, factory_name, factory_address, packaging_method, weight, storage_method, eat_method, create_time, update_time) VALUES ('4', '21', '中国湖北武汉', '12个月', '卫龙', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO store.product_params (param_id, product_id, product_place, foot_period, brand, factory_name, factory_address, packaging_method, weight, storage_method, eat_method, create_time, update_time) VALUES ('5', '22', '中国湖北武汉', '12个月', '威龙', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO store.product_params (param_id, product_id, product_place, foot_period, brand, factory_name, factory_address, packaging_method, weight, storage_method, eat_method, create_time, update_time) VALUES ('6', '23', '中国湖北武汉', '12个月', '老干妈', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO store.product_params (param_id, product_id, product_place, foot_period, brand, factory_name, factory_address, packaging_method, weight, storage_method, eat_method, create_time, update_time) VALUES ('7', '24', '中国湖北武汉', '12个月', '卫龙', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
INSERT INTO store.product_params (param_id, product_id, product_place, foot_period, brand, factory_name, factory_address, packaging_method, weight, storage_method, eat_method, create_time, update_time) VALUES ('8', '1', '中国湖北武汉', '12个月', '良品铺子', '贤磊咪咪虾条有限责任公司', '湖北武汉江夏区光谷大道77号', '袋装', '50g', '常温保存', '开袋即食', '2021-04-27 11:53:20', '2021-04-27 11:53:35');
