create table notification               --通知
(
	id bigint,                          --通知的ID
	notifier Bigint not null,           --通知者的ID
	receiver Bigint not null,           --被通知者的ID
	outer_id BIGINT not null,           --问题的ID
	type int not null,                  --回复评论或者回复问题
	gmt_create BIGINT not null,         --创建时间
	status int default 0 not null,      --已读或者是未读
	constraint notification_pk
		primary key (id)
);