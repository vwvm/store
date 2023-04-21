create database if not exists paperManagement;
use paperManagement;

# 部门表
create table if not exists school
(
    id              int auto_increment comment '序号',
    school_name     varchar(64)                        null comment '名称',
    school_describe text                               null comment '描述',
    creation_time   datetime default current_timestamp null comment '创建时间',
    update_time     datetime default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id  VARCHAR(128)                       null comment '更新人id',
    delete_flag     boolean  default false             null comment '删除标记',
    constraint school_pk
        primary key (id)
)
    comment '用于记录学校信息';

insert school(id, school_name, school_describe)
values (1, '龙岩学院', '龙岩学院位于全国著名革命老区、客家祖地、全国文明城市——福建省龙岩市,是福建省示范性应用型本科高校、福建省硕士学位授予培育单位立项建设高校、福建省文明校园。')
