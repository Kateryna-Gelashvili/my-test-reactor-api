create table root.post
(
	id bigserial not null,
	author varchar,
	article text,
	created_at timestamp default current_timestamp not null,
	updated_at timestamp default current_timestamp not null
);

create unique index post_id_uindex
	on root.post (id);

alter table root.post
	add constraint post_pk
		primary key (id);

