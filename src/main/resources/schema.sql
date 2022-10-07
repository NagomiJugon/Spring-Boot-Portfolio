create table app_user (
  id int not null auto_increment,
  name varchar(100) not null,
  password varchar(100) not null,
  created datetime not null,
  primary key(id)
);
create table tweets (
  id int not null auto_increment,
  user_name varchar(100) not null,
  content varchar(1000) not null,
  created datetime not null,
  primary key(id)
);