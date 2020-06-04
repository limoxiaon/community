create table question
(
	id int auto_increment,         --问题的唯一ID标识
	title VARCHAR(50),             --问题的题目
	description TEXT,              --问题的具体描述
	gmt_create  BIGINT,            --创建时间
	gmt_modified BIGINT,           --修改时间
	creator int,                   --（创建人）用户的ID（用户表的ID和ACCOUNT_ID都成为了唯一标识）
	comment_count int default 0,   --评论数
	view_count int default 0,      --阅读数
	like_count int default 0,      --点赞数
	tag VARCHAR(256),              --标签
	constraint question_pk
		primary key (id)
);
