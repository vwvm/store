create database if not exists paperManagement;

# 用户表
create table if not exists user
(
    id             int auto_increment comment '序号',
    user_username  VARCHAR(128)                       null comment '用户名，可用于登录',
    user_password  VARCHAR(128)                       null comment '用户密码，可用于登录',
    user_telephone VARCHAR(128)                       null comment '用户电话，可用于登录',
    user_email     VARCHAR(128)                       null comment '用户邮箱，可用于登录',
    user_roles     int      default 1                 null comment '用户角色，0系统管理，1访客，其他自定义',
    creation_time  datetime default current_timestamp null comment '创建时间',
    update_time    datetime default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id int                                null comment '更新人id',
    delete_flag    boolean  default false             null comment '删除标记',
    constraint user_pk
        primary key (id),
    constraint user_uk
        unique (user_username, user_telephone, user_email)
)
    comment '用于记录系统的所有用户';


# 学校表
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

# 部门表
create table if not exists department
(
    id                  int auto_increment comment '序号',
    school_id           int                                null comment '外键，学校，id',
    department_name     varchar(64)                        null comment '名称',
    department_describe text                               null comment '部门描述',
    creation_time       datetime default current_timestamp null comment '创建时间',
    update_time         datetime default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id      VARCHAR(128)                       null comment '更新人id',
    delete_flag         boolean  default false             null comment '删除标记',
    constraint department_pk
        primary key (id),
    constraint department_school_id_fk
        foreign key (school_id) references school (id)
)
    comment '部门表';


# 教师信息表
create table if not exists teacher
(
    id                  int auto_increment comment '序号',
    user_id             int                                null comment '外键，用户表，id',
    department_id       int                                null comment '外键，部门表，id',
    teacher_name        varchar(64)                        null comment '教师名字',
    teacher_describe    text                               null comment '教师描述',
    teacher_project_num int                                null comment '教师管理课题数',
    teacher_rank        varchar(32)                        null comment '教师职称',
    creation_time       datetime default current_timestamp null comment '创建时间',
    update_time         datetime default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id      VARCHAR(128)                       null comment '更新人id',
    delete_flag         boolean  default false             null comment '删除标记',
    constraint teacher_pk
        primary key (id),
    constraint teacher_user_id_fk
        foreign key (user_id) references user (id),
    constraint teacher_department_id_fk
        foreign key (department_id) references department (id)
)
    comment '用于记录教师的详细信息';

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
        primary key (id),
    constraint student_user_id_fk
        foreign key (user_id) references user (id),
    constraint student_major_id_fk
        foreign key (major_id) references major (id),
    constraint student_teacher_id_fk
        foreign key (teacher_id) references teacher (id)
)
    comment '用于记录教师的详细信息';



