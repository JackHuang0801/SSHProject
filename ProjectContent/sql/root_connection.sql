show databases;

--create database DEV_MYSQL_LIVE;
--drop database DEV_MYSQL_LIVE;

--create database MARS_DEV_MYSQL;
--drop database MARS_DEV_MYSQL;

create database luna_dev_live;
drop database luna_dev_live;

GRANT USAGE ON *.* TO 'Jack_Huang'@'localhost' IDENTIFIED BY '123456';

grant all privileges on luna_dev_live.* to 'Jack_Huang'@'localhost';

flush privileges;



SET FOREIGN_KEY_CHECKS = 0;
USE `LUNA_DEV_LIVE`;

DROP TABLE IF EXISTS `LUNA_DEV_LIVE`.`T_USER`;
DROP TABLE IF EXISTS `LUNA_DEV_LIVE`.`T_ROLE`;
DROP TABLE IF EXISTS `LUNA_DEV_LIVE`.`T_RESOURCE`;
DROP TABLE IF EXISTS `LUNA_DEV_LIVE`.`T_DEPARTMENT`;
DROP TABLE IF EXISTS `LUNA_DEV_LIVE`.`USER_ROLE`;
DROP TABLE IF EXISTS `LUNA_DEV_LIVE`.`ROLE_RESOURCE`;

SET FOREIGN_KEY_CHECKS = 1;



--grant 权限 on 数据库对象 to 用户;

/*
1.新建用户。
//登录MYSQL
 @>mysql -u root -p
 @>密码
 //创建用户
 mysql> insert into mysql.user(Host,User,Password) values("localhost","phplamp",password("1234"));
 //刷新系统权限表
 mysql>flush privileges;
 这样就创建了一个名为：phplamp  密码为：1234  的用户。
*/

-- 以下方式会报错
--insert into mysql.user(Host,User,Password) values ("localhost","Jack Huang",password("654321"));

delete from mysql.user where user='Jack_Huang';

GRANT USAGE ON *.* TO 'Jack_Huang'@'localhost' IDENTIFIED BY '123456';

grant all privileges on mars_dev_live.* to 'Jack_Huang'@'localhost';

flush privileges;

/*
三、grant 普通 DBA 管理某个 MySQL 数据库的权限。
grant all privileges on testdb to dba@'localhost' 
其中，关键字 “privileges” 可以省略。

四、grant 高级 DBA 管理 MySQL 中所有数据库的权限。
grant all on *.* to dba@'localhost'
*/


select @@autocommit;

set autocommit=0;

set autocommit=1;

select * from mysql.user;

select * from user;

UPDATE mysql.user set password='123456' where user='Jack_Huang';

delete from MYSQL.user where user is null;
delete from MYSQL.user where user='Jack_Huang';
FLUSH PRIVILEGES;