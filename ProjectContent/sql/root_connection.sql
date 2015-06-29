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



--grant Ȩ�� on ���ݿ���� to �û�;

/*
1.�½��û���
//��¼MYSQL
 @>mysql -u root -p
 @>����
 //�����û�
 mysql> insert into mysql.user(Host,User,Password) values("localhost","phplamp",password("1234"));
 //ˢ��ϵͳȨ�ޱ�
 mysql>flush privileges;
 �����ʹ�����һ����Ϊ��phplamp  ����Ϊ��1234  ���û���
*/

-- ���·�ʽ�ᱨ��
--insert into mysql.user(Host,User,Password) values ("localhost","Jack Huang",password("654321"));

delete from mysql.user where user='Jack_Huang';

GRANT USAGE ON *.* TO 'Jack_Huang'@'localhost' IDENTIFIED BY '123456';

grant all privileges on mars_dev_live.* to 'Jack_Huang'@'localhost';

flush privileges;

/*
����grant ��ͨ DBA ����ĳ�� MySQL ���ݿ��Ȩ�ޡ�
grant all privileges on testdb to dba@'localhost' 
���У��ؼ��� ��privileges�� ����ʡ�ԡ�

�ġ�grant �߼� DBA ���� MySQL ���������ݿ��Ȩ�ޡ�
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