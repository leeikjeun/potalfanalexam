create table user(
id int(255) not null AUTO_INCREMENT,
user_id varchar(255) not null,
name varchar(255) not null,
password varchar(255) not null,
PRIMARY KEY (id)
);

create table blog(
id int(255) not null auto_increment,
title varchar(255) not null,
content varchar(255) not null,
date date,
catalog_id int(255) not null,
user_id int(255) not null,
primary key(id),
);

create table catalog(
id int(255) not null auto_increment,
name varchar(255) not null,
primary key(id);
);

create table comment(
id int(255) not null auto_increment,
content varchar(255) not null,
date_comment date,
blog_id int(255) not null,
user_id int(255) not null,
primary key(id)
);

insert into user VALUES (1,'id','name','pass');
insert into user VALUES (2,'ids','name','pass');


insert into catalog values (1,'test')
insert into catalog values (2,'tset2')

insert into blog values (1,'testitle','testcontent',NOW(),1,1);
insert into blog values (2,'testitle','testcontent',NOW(),1,1);
insert into blog values (3,'testitle','testcontent',NOW(),1,1);

insert into comment values (1,'testcontent',NOW(),1,1);
insert into comment values (2,'testcontent',NOW(),1,1);\
insert into comment values (3,'testcontent',NOW(),1,1);



