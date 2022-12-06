
-- 1. Member

DROP TABLE IF EXISTS Member;

CREATE TABLE Member (
	`UserId` bigint(18) NOT NULL AUTO_INCREMENT comment '用户Id',
	`UserName` varchar(20) NOT NULL DEFAULT '' comment '账号',
	`UserPass` varchar(32) NOT NULL DEFAULT '' comment '密码',
	`NickName` varchar(20) NOT NULL DEFAULT '' comment '昵称',
	`Email` varchar(32) NOT NULL DEFAULT '' comment '邮件',
	`Mobile` varchar(20) NULL DEFAULT '' comment '手机',

	`MyId` varchar(50)  NULL DEFAULT '' comment 'myId',
	`MyIdKey` varchar(50) NULL DEFAULT '' comment 'myIdKey',
	`RegIp` varchar(50) NULL DEFAULT '' comment '注册IP',
	`RegDate` datetime  NULL DEFAULT now() comment '注册时间',
	`LastLoginIp` varchar(50) NULL DEFAULT '0' comment '最后登录IP',
	`LastLoginTime` datetime  NULL comment '最后登录时间',
	`Salt` varchar(6) NULL DEFAULT 'Liuvei' comment 'salt',
	`Secques` varchar(8) NULL default '' comment 'secques',

  `Status` varchar(20) null DEFAULT '' comment '状态',
  `Remark` varchar(200) null DEFAULT '' comment '备注',
  `SortNum` bigint(18) null DEFAULT 0 comment '排序',
  `IsDeleted` bigint(18) null DEFAULT 0 comment '是否删除',
  
  `CreateBy` bigint(18) null DEFAULT 0 comment '创建用户',
  `UpdateBy` bigint(18) null DEFAULT 0 comment '更新用户',
  `CreateOn` datetime null DEFAULT now() comment'创建时间',
  `UpdateOn` datetime null DEFAULT now() comment'更新时间',

	PRIMARY KEY(UserId)
) comment '用户' character set UTF8;

-- 为Member添加约束
ALTER TABLE Member
Add CONSTRAINT uq_Member_UserName Unique Key(UserName);

ALTER TABLE Member
Add CONSTRAINT uq_Member_NickName Unique Key(NickName);

ALTER TABLE Member
Add CONSTRAINT uq_Member_Email Unique Key(Email);




