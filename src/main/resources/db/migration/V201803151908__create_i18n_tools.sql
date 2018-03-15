create table i18n_tools (
  id int,
  language_id int,
  name varchar(256) not null,
  description varchar(1024) not null,
  foreign key (id) references tags(id),
  foreign key (language_id) references languages(id)
)
