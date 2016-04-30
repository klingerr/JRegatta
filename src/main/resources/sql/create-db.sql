drop table user if exists;
create table user (
-- hsqldb: user_id integer primary key GENERATED BY DEFAULT AS IDENTITY(START WITH 100),
  user_id integer auto_increment primary key,
  username varchar(50) not null,
  email varchar(50) not null,
  pw varchar(255) not null
);

drop table follower if exists;
create table follower (
  follower_id integer,
  followee_id integer
);

drop table message if exists;
create table message (
-- hsqldb: message_id integer primary key GENERATED BY DEFAULT AS IDENTITY(START WITH 100),
  message_id integer auto_increment primary key,
  author_id integer not null,
  text varchar(160) not null,
  pub_date timestamp
);