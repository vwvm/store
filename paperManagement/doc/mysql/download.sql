create database if not exists paperManagement;
use paperManagement;

#资料索引表
create table if not exists index_download
(
    id               int auto_increment comment '序号',
    user_id          varchar(64)                        null comment '外键，用户表，id',
    data_index1      varchar(16)                        null comment '一级标签，表示过程',
    data_index2      varchar(16)                        null comment '二级标签，过程表中的序号',
    audit_mark       int      default 1                 null comment '审核标记，默认0不通过，1审核通过',
    data_download_id varchar(64)                        null comment '外键，下载信息表，id',
    creation_time    datetime default current_timestamp null comment '创建时间',
    update_time      datetime default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id   VARCHAR(128)                       null comment '更新人id',
    delete_flag      boolean  default false             null comment '删除标记',
    constraint index_download_pk
        primary key (id)
)
    comment '资源下载信息索引';

insert into index_download(id, user_id, data_index1, data_index2, data_download_id)
values (1, 21, 1, 1, 20101);

# 资料下载信息表
create table if not exists data_download
(
    id             int auto_increment comment '序号',
    data_name      varchar(64)                        null comment '资源名称',
    data_describe  text                               null comment '资源描述',
    data_url       VARCHAR(128)                       null comment '资源下载路径',
    creation_time  datetime default current_timestamp null comment '创建时间',
    update_time    datetime default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id VARCHAR(128)                       null comment '更新人id',
    delete_flag    boolean  default false             null comment '删除标记',
    constraint school_pk
        primary key (id)
)
    comment '资源下载信息信息';

insert data_download(data_name, data_describe, data_url)
values ('龙岩学院毕业论文(设计)工作管理规定', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('毕业论文(设计)选题审核表', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('毕业论文(设计)承诺保证书', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('毕业论文(设计)开题报告', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('毕业论文(设计)指导过程记录表', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('毕业论文(设计)中期检查情况表(学生填表)', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('毕业论文(设计)中期检查情况表(指导教师填表)', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('毕业论文(设计)指导教师成绩评定表', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('毕业论文(设计)答辩评分表', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('毕业论文(设计)答辩记录表', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('毕业论文(设计)综合成绩评定表', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('论文检测系统操作指南', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('毕业论文(设计)应上交的材料', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('优秀毕业论文(设计)答辩记录与成绩评定表', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(data_name, data_describe, data_url)
values ('优秀毕业论文(设计)推荐表', '范化要求', '2023届毕业论文(设计）指导手册（印刷稿）.docx');
insert data_download(id, data_name, data_describe, data_url)
values (20101, '开题报告', '范化要求', '2023届毕业论文开题报告.docx');
