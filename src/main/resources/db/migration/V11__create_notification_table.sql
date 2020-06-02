create table notification
(
	id bigint,
	notifier Bigint not null,
	receiver Bigint not null,
	outer_id BIGINT not null,
	type int not null,
	gmt_create BIGINT not null,
	status int default 0 not null,
	constraint notification_pk
		primary key (id)
);