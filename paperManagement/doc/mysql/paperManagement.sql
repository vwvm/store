create database if not exists paperManagement;

# 学校表


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
        primary key (id),
    constraint major_school_id_fk
        foreign key (school_id) references school (id)
)
    comment '用于记录学科专业信息';





# 学生信息表
create table if not exists student
(
    id               int auto_increment comment '序号',
    user_id          int                                null comment '外键，用户表，id',
    major_id         int                                null comment '外键，学科专业表，id',
    teacher_id       int                                null comment '外键，教师表，id, 导师名字',
    student_name     varchar(64)                        null comment '学生名字',
    student_describe text                               null comment '学生描述',
    student_class    varchar(64)                        null comment '学生所在班级',
    student_score    int                                null comment '学生成绩',
    creation_time    datetime default current_timestamp null comment '创建时间',
    update_time      datetime default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id   VARCHAR(128)                       null comment '更新人id',
    delete_flag      boolean  default false             null comment '删除标记',
    constraint student_pk
        primary key (id)
)
    comment '用于记录教师的详细信息';



