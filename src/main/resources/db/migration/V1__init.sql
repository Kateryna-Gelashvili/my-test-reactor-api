create table post
(
	id bigserial primary key,
	author varchar,
	article text,
	created_at timestamp default current_timestamp not null,
	updated_at timestamp default current_timestamp not null
);

