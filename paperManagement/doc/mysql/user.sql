create database if not exists paperManagement;
use paperManagement;

#  用户表
create table if not exists user
(
    id             int auto_increment comment '序号',
    user_username  VARCHAR(128)                       null comment '用户名，可用于登录',
    user_password  VARCHAR(128)                       null comment '用户密码，可用于登录',
    user_telephone VARCHAR(128)                       null comment '用户电话，可用于登录',
    user_email     VARCHAR(128)                       null comment '用户邮箱，可用于登录',
    user_roles     json                               null comment '用户角色，json数据，list',
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

insert user(id, user_username, user_password, user_telephone, user_email, user_roles)
values (1, 'admin', 'admin', 15695912345, '15695912345@qq.com', '[
  "ROLE_admin"
]');
insert user(id, user_username, user_password, user_telephone, user_email, user_roles)
values (2, 'admin', '21232f297a57a5a743894a0e4a801fc3', 15695912345, '15695912346@qq.com', '[
  "ROLE_admin"
]');
insert user(id, user_username, user_password, user_telephone, user_email, user_roles)
values (3, 'teacher', '21232f297a57a5a743894a0e4a801fc3', 15695912345, '15695912347@qq.com', '[
  "ROLE_teacher"
]');
insert user(id, user_username, user_password, user_telephone, user_email, user_roles)
values (4, 'student', '21232f297a57a5a743894a0e4a801fc3', 15695912345, '15695912348@qq.com', '[
  "ROLE_student"
]');