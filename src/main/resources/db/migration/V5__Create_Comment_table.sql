create table comment                    --评论包括二级评论
(
	id Bigint auto_increment,           --评论的ID
	parent_id BIGINT not null,          --评论的父ID
	type int not null,                  --判断是提问还是回复
	commentator int not null,           --评论者
	gmt_create bigint not null,         --创建时间
	gmt_modified BIGINT not null,       --修改时间
	like_count BIGINT default 0,        --点赞数
	constraint comment_pk
		primary key (id)
);