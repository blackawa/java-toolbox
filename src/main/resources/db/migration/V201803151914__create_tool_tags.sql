create table tool_tags (
  tool_id int not null,
  tag_id int not null,
  foreign key (tool_id) references tools(id),
  foreign key (tag_id) references tags(id)
)
