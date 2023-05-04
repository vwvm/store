create database if not exists paperManagement;
use paperManagement;

# 学科专业表
create table if not exists major
(
    id             int auto_increment comment '序号',
    school_id      int                                null comment '外键，学校，id',
    major_name     varchar(64)                        null comment '名称',
    major_describe text                               null comment '描述',
    creation_time  datetime default current_timestamp null comment '创建时间',
    update_time    datetime default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id VARCHAR(128)                       null comment '更新人id',
    delete_flag    boolean  default false             null comment '删除标记',
    constraint major_pk
        primary key (id)
)
    comment '用于记录学科专业信息';

insert into major(id, school_id, major_name, major_describe)
VALUES(1, 1, '数学与信息工程学院 软件工程', '数学与信息工程学院');






