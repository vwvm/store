

-- 1. news_cat

DROP TABLE IF EXISTS news_cat;

CREATE TABLE news_cat (
  `CatId` bigint(18) NOT NULL AUTO_INCREMENT comment 'Id',
  `CatName` varchar(20) NOT NULL DEFAULT '' comment '类目名称',
  `CatDesc` varchar(32) NULL DEFAULT '' comment '类目描述',
  `ParentId` bigint(18) NOT NULL DEFAULT 0 comment '父Id',

  `Status` varchar(20) null DEFAULT '' comment '状态',
  `Remark` varchar(200) null DEFAULT '' comment '备注',
  `SortNum` bigint(18) null DEFAULT 0 comment '排序',
  `IsDeleted` bigint(18) null DEFAULT 0 comment '是否删除',
  
  `CreateBy` bigint(18) null DEFAULT 0 comment '创建用户',
  `UpdateBy` bigint(18) null DEFAULT 0 comment '更新用户',
  `CreateOn` datetime null DEFAULT now() comment'创建时间',
  `UpdateOn` datetime null DEFAULT now() comment'更新时间',
 
	PRIMARY KEY(CatId)
) comment '新闻类目' character set UTF8;

-- 为news_cat添加约束
ALTER TABLE news_cat
ADD CONSTRAINT uq_news_cat_CatName Unique Key(CatName);




-- 2. News

DROP TABLE IF EXISTS News;

CREATE TABLE News (
	`NewsId` bigint(18) NOT NULL AUTO_INCREMENT comment 'Id',
	`CatId` bigint(18) NOT NULL comment '类目Id',

	`Title` varchar(200) NOT NULL comment '标题',
	`Author` varchar(50) NOT NULL comment '作者',

	`Summary` varchar(2000) NOT NULL comment '摘要',
	`PicPath` varchar(255) DEFAULT NULL comment '图片路径',


  `Status` varchar(20) null DEFAULT '' comment '状态',
  `Remark` varchar(200) null DEFAULT '' comment '备注',
  `SortNum` bigint(18) null DEFAULT 0 comment '排序',
  `IsDeleted` bigint(18) null DEFAULT 0 comment '是否删除',
  
  `CreateBy` bigint(18) null DEFAULT 0 comment '创建用户',
  `UpdateBy` bigint(18) null DEFAULT 0 comment '更新用户',
  `CreateOn` datetime null DEFAULT now() comment'创建时间',
  `UpdateOn` datetime null DEFAULT now() comment'更新时间',


	primary key (NewsId)
) comment '新闻' character set UTF8;

-- 为News添加约束




-- 3. NewsExCont

DROP TABLE IF EXISTS NewsExCont;

CREATE TABLE NewsExCont (
	`Id` bigint(18) NOT NULL AUTO_INCREMENT comment 'Id',
	`NewsId` bigint(18) NOT NULL comment 'NewsId',

	`Content` text NULL comment '内容',

  `Status` varchar(20) null DEFAULT '' comment '状态',
  `Remark` varchar(200) null DEFAULT '' comment '备注',
  `SortNum` bigint(18) null DEFAULT 0 comment '排序',
  `IsDeleted` bigint(18) null DEFAULT 0 comment '是否删除',
  
  `CreateBy` bigint(18) null DEFAULT 0 comment '创建用户',
  `UpdateBy` bigint(18) null DEFAULT 0 comment '更新用户',
  `CreateOn` datetime null DEFAULT now() comment'创建时间',
  `UpdateOn` datetime null DEFAULT now() comment'更新时间',


	primary key (Id)
) comment '新闻内容' character set UTF8;

-- 为 NewsExCont 添加约束

ALTER TABLE NewsExCont
Add CONSTRAINT uq_NewsExCont_NewsId Unique Key(NewsId);


-- 4. NewsComment

DROP TABLE IF EXISTS NewsComment;

CREATE TABLE NewsComment (

	`Id` bigint(18) not null AUTO_INCREMENT comment '评论Id',
	`NewsID` bigint(18) NOT NULL comment '新闻Id',
	`Content` varchar(2000) NOT NULL comment '评论内容',

	`Author` varchar(50) NOT NULL comment '评论作者',
	`FromIP` varchar(100)  NULL comment '来源IP',


  `Status` varchar(20) null DEFAULT '' comment '状态',
  `Remark` varchar(200) null DEFAULT '' comment '备注',
  `SortNum` bigint(18) null DEFAULT 0 comment '排序',
  `IsDeleted` bigint(18) null DEFAULT 0 comment '是否删除',
  
  `CreateBy` bigint(18) null DEFAULT 0 comment '创建用户',
  `UpdateBy` bigint(18) null DEFAULT 0 comment '更新用户',
  `CreateOn` datetime null DEFAULT now() comment'创建时间',
  `UpdateOn` datetime null DEFAULT now() comment'更新时间',

	primary key (Id)


) comment '新闻评论' character set UTF8;

-- 为NewsComment添加约束


