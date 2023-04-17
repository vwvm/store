create database if not exists paperManagement;
use paperManagement;

# 权限信息表
create table if not exists role
(
    id             int auto_increment comment '序号',
    role_name      varchar(64)                        null comment '权限名字, 英文',
    role_describe  text                               null comment '权限描述，',
    creation_time  datetime default current_timestamp null comment '创建时间',
    update_time    datetime default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id VARCHAR(128)                       null comment '更新人id',
    delete_flag    boolean  default false             null comment '删除标记',
    constraint teacher_pk
        primary key (id)
)
    comment '用于记录教师的详细信息';

INSERT INTO role(id, role_name, role_describe) values (0, 'admin', '管理员，管理系统的根权限')
ON DUPLICATE KEY UPDATE role_name='admin', role_describe = '管理员，管理系统的根权限';
INSERT INTO role(id, role_name, role_describe) values (1, 'user', '用户，普通访问权限')
ON DUPLICATE KEY UPDATE role_name='user', role_describe = '用户，普通访问权限';
INSERT INTO role(id, role_name, role_describe) values (2, 'teacher', '教师，教师访问权限')
ON DUPLICATE KEY UPDATE role_name='teacher', role_describe = '教师，教师访问权限';