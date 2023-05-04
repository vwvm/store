create database if not exists paperManagement;
use paperManagement;

DROP TABLE IF EXISTS `project1`;
CREATE TABLE `project1`
(
    `id`               int                                                     NOT NULL AUTO_INCREMENT COMMENT '主键,课题id',
    `project_name`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '课题名',
    `project_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课题表述',
    `project_from`     varchar(50)                                             NULL DEFAULT NULL COMMENT '外键,课题来源（老师建议等等）',
    `teacher_id`       char(32) CHARACTER SET utf8 COLLATE utf8_general_ci     NULL DEFAULT NULL COMMENT '外键,导师id',
    `student_id`       char(32) CHARACTER SET utf8 COLLATE utf8_general_ci     NULL DEFAULT NULL COMMENT '外键,学生的用户id',
    `project_state`    int                                                          DEFAULT 0 COMMENT '该选题的状态，默认状态为0,有老师和学生，该课题已选，有老师没学生可以选；为1时，待审核，不可选；选题状态2，不可选。',
    creation_time      datetime                                                     default current_timestamp null comment '创建时间',
    update_time        datetime                                                     default current_timestamp null on update current_timestamp comment '更新时间',
    update_user_id     VARCHAR(128)                                            null comment '更新人id',
    delete_flag        boolean                                                      default false null comment '删除标记',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `teacher_id` (`teacher_id`) USING BTREE,
    INDEX `student_id` (`student_id`) USING BTREE,
    INDEX `project_from` (project_from) USING BTREE
) comment '课题信息表';

insert project(id, project_name, project_describe, project_from, teacher_id, student_id, project_state)
values (1, '基于vue和springboot的毕业论文管理系统', '无', '无', 1, 2, 2);
insert project(project_name, project_describe, project_from, teacher_id, student_id, project_state)
values ('基于vue和springboot的毕业论文管理系统', '无', '无', 1, 2, 1);
insert project(project_name, project_describe, project_from, teacher_id, student_id, project_state)
values ('基于vue和springboot的毕业论文管理系统', '无', '无', 1, 2, 0);

