create database if not exists paperManagement;
use paperManagement;

# 教师信息表
create table if not exists teacher
(
    id                  int auto_increment comment '序号',
    user_id             int                                null comment '对应，用户表，id',
    department_id       int                                null comment '对应，部门表，id',
    teacher_name        varchar(64)                        null comment '教师名字',
    teacher_describe    text                               null comment '教师描述',
    teacher_project_num int                                null comment '教师管理课题数',
    teacher_rank        varchar(32)                        null comment '教师职称',
    creation_time       datetime default current_timestamp null comment '创建时间',
    update_time         datetime default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id      VARCHAR(128)                       null comment '更新人id',
    delete_flag         boolean  default false             null comment '删除标记',
    constraint teacher_pk
        primary key (id)
)
    comment '用于记录教师的详细信息';

insert teacher(id, user_id, department_id, teacher_name, teacher_describe, teacher_project_num, teacher_rank)
values (1, 111, 1, '小明', '用来测试使用的', 1, '未知');
insert teacher(id, user_id, department_id, teacher_name, teacher_describe, teacher_project_num, teacher_rank)
values (2, 112, 1, '小红', '用来测试使用的', 2, '未知');
insert teacher(id, user_id, department_id, teacher_name, teacher_describe, teacher_project_num, teacher_rank)
values (3, 113, 2, '小张', '用来测试使用的', 3, '未知');
insert teacher(id, user_id, department_id, teacher_name, teacher_describe, teacher_project_num, teacher_rank)
values (4, 114, 3, '小黄', '用来测试使用的', 4, '未知');
insert teacher(id, user_id, department_id, teacher_name, teacher_describe, teacher_project_num, teacher_rank)
values (5, 115, 4, '小绿', '用来测试使用的', 5, '未知');
