create table comment
(
	id Bigint auto_increment,
	parent_id BIGINT not null,
	type int not null,
	commentator int not null,
	gmt_create bigint not null,
	gmt_modified BIGINT not null,
	like_count BIGINT default 0,
	constraint comment_pk
		primary key (id)
);