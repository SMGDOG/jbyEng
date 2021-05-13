use jbyEng

if exists(select * from sysobjects where name='user_info')
drop table user_info
go
create table user_info
(
id varchar(255) not null,
name varchar(255),
learn_table varchar(255),
learn_num int,
learn_sum int,
review int
)