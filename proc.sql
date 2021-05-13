use jbyEng

if exists (select * from sysobjects where name='search_word')
drop proc search_word
go
create proc search_word(
@word nvarchar(255),
@pron_a nvarchar(255) out,
@pron_e nvarchar(255) out,
@tran nvarchar(255) out,
@type nvarchar(255) out
)
as
select @pron_a=ÃÀÊ½Òô±ê, @pron_e=Ó¢Ê½Òô±ê, @tran=½âÊÍ, @type=´ÊÐÔ from word where Word=@word;
go

if exists (select * from sysobjects where name='login')
drop proc login
go
create proc login(
@id varchar(255)
)
as
begin
if  not exists (select * from user_info where id=@id)
begin
insert into user_info values(@id, '±³Ó¢ÓïµÄ¼Ö±¦Óñ', 'CET4', 0, 0, 0)
end;
end;
go

if exists (select * from sysobjects where name='changeName')
drop proc changeName
go
create proc changeName(
@id varchar(255),
@name varchar(255)
)
as
begin
if exists (select * from user_info where id=@id)
begin
update user_info set name=@name where id=@id
end;
end;
go

if exists (select * from sysobjects where name='learn_log')
drop proc learn_log
go
create proc learn_log(
@id varchar(255),
@num int,
@table_name varchar(255)
)
as
begin
if exists (select * from user_info where id=@id)
begin
update user_info set learn_table=@table_name,learn_num=@num,learn_sum=0 where id=@id
end;
end;
go

if exists (select * from sysobjects where name='reset_log')
drop proc reset_log
go
create proc reset_log(
@id varchar(255)
)
as
begin
if exists (select * from user_info where id=@id)
begin
update user_info set learn_sum=0,review=0 where id=@id
end;
end;
go

if exists (select * from sysobjects where name='get_log')
drop proc get_log
go
create proc get_log(
@id varchar(255),
@num int
)
as
begin
if exists (select * from user_info where id=@id)
begin
update user_info set learn_sum=learn_sum+@num where id=@id
end;
end;
go

if exists (select * from sysobjects where name='get_learninfo')
drop proc get_learninfo
go
create proc get_learninfo(
@id varchar(255),
@name varchar(255) out,
@table varchar(255) out,
@num int out,
@sum int out,
@review int out
)
as
begin
if exists (select * from user_info where id=@id)
begin
select @name=name,@table=learn_table,@num=learn_num,@sum=learn_sum,@review=review from user_info where id=@id
end;
end;
go

if exists (select * from sysobjects where name='reset_num')
drop proc reset_num
go
create proc reset_num(
@id varchar(255),
@num int
)
as
begin
if exists (select * from user_info where id=@id)
begin
update user_info  set learn_num=@num where id=@id
end;
end;
go

if exists (select * from sysobjects where name='get_learn_list_CET4')
drop proc get_learn_list_CET4
go
create proc get_learn_list_CET4(
@first int,
@last int
)
as
select * from CET4 where ÐòºÅ>=@first and ÐòºÅ<=@last
go

if exists (select * from sysobjects where name='get_learn_list_CET6')
drop proc get_learn_list_CET6
go
create proc get_learn_list_CET6(
@first int,
@last int
)
as
select * from CET6 where ÐòºÅ>=@first and ÐòºÅ<=@last
go

if exists (select * from sysobjects where name='get_review')
drop proc get_review
go
create proc get_review(
@id varchar(255),
@num int
)
as
begin
if exists (select * from user_info where id=@id)
begin
update user_info set review=review+@num where id=@id
end;
end;
go

if exists (select * from sysobjects where name='reset_review')
drop proc reset_review
go
create proc reset_review(
@id varchar(255)
)
as
begin
if exists (select * from user_info where id=@id)
begin
update user_info set review=0 where id=@id
end;
end;