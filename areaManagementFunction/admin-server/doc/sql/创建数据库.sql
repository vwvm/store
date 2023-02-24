create schema areaManagementFunction;
# 这里简单创建一个用户表
create table user
(
    id            int auto_increment comment '序号',
    username      varchar(20)                        null comment '用户名',
    password      varchar(255)                       null comment '密码',
    creation_time datetime default current_timestamp null comment '创建时间',
    update_time   datetime default current_timestamp null on update current_timestamp comment '修改时间',
    delete_flag   boolean  default false             null comment '删除标记',
    constraint user_pk
        primary key (id)
);
INSERT INTO areaManagementFunction.user (username, password)
VALUES ('admin', '$2a$10$eF0fZ5UE7nIkrdbM6XFFl.4UuvJTIRl9XXVsKdMTv9Zq0wwoJPhYO');

# 一个区域表
create table region
(
    id            int auto_increment comment '序号',
    region_name   varchar(20)                        null comment '区域名称',
    regional_city json                               null comment '区域城市',
    address       json                               null comment '地址',
    address_size  json                               null comment '用于描述前一列地址的多少',
    creation_time datetime default current_timestamp null comment '创建时间',
    update_time   datetime default current_timestamp null on update current_timestamp comment '修改时间',
    delete_flag   boolean  default false             null comment '删除标记',
    constraint region_pk
        primary key (id)
);
INSERT INTO areaManagementFunction.region (region_name, regional_city, address, address_size)
VALUES ('华南区城', '[
  "深圳",
  "广州"
]', '[
  "深圳-深圳市福田区北环大道通业大厦南培五层501",
  "广州一广州市越秀区东湖路123号"
]','[
  2,
  2
]');
INSERT INTO areaManagementFunction.region (region_name, regional_city, address, address_size)
VALUES ('华东区域', '[
  "上海",
  "杭州"
]', '[
  "上海-上海市浦东新区锦锅路1001号",
  "杭州-杭州市上城区方裕岭路81号"
]','[
  2,
  2
]');

# 城市信息表
create table city
(
    id            int auto_increment comment '序号',
    city          varchar(20)                        null comment '城市',
    creation_time datetime default current_timestamp null comment '创建时间',
    update_time   datetime default current_timestamp null on update current_timestamp comment '修改时间',
    delete_flag   boolean  default false             null comment '删除标记',
    constraint city_pk
        primary key (id),
    constraint city_unique
        unique (city)
);
INSERT INTO areaManagementFunction.city (city)
VALUES ('上海');
INSERT INTO areaManagementFunction.city (city)
VALUES ('北京');
INSERT INTO areaManagementFunction.city (city)
VALUES ('深圳');
INSERT INTO areaManagementFunction.city (city)
VALUES ('广州');
INSERT INTO areaManagementFunction.city (city)
VALUES ('杭州');
INSERT INTO areaManagementFunction.city (city)
VALUES ('厦门');