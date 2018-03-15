create table tool_tags (
  tool_id int,
  tag_id int,
  primary key (tool_id, tag_id),
  foreign key (tool_id) references tools(id),
  foreign key (tag_id) references tags(id)
)
