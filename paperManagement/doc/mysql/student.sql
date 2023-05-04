create database if not exists paperManagement;
use paperManagement;

create table if not exists student
(
    id               int auto_increment comment '序号',
    user_id          int                                   null comment '外键，用户表，id',
    major_id         int                                   null comment '外键，学科专业表，id',
    teacher_id       varchar(64) default 0                 not null comment '外键，教师表，id',
    student_state    int         default 0                 null comment '状态，0 未选题',
    student_name     varchar(64)                           null comment '学生名字',
    student_describe text                                  null comment '学生描述',
    student_class    varchar(64)                           null comment '学生所在班级',
    student_score    int                                   null comment '学生成绩',
    creation_time    datetime    default current_timestamp null comment '创建时间',
    update_time      datetime    default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id   VARCHAR(128)                          null comment '更新人id',
    delete_flag      boolean     default false             null comment '删除标记',
    constraint student_pk
        primary key (id)
)
    comment '用于记录教师的详细信息';


insert into student(id, user_id, major_id, teacher_id, student_state, student_name, student_describe, student_class,
                    student_score)
VALUES (1, 21, 1, 1, 0, '学生1', '学生1测试用', '19级软件工程专业', 0)
