create table i18n_tags (
  id int,
  language_id int not null,
  name varchar(256) not null,
  description varchar(1024) null,
  primary key (id, language_id),
  foreign key (id) references tags(id),
  foreign key (language_id) references languages(id)
)
