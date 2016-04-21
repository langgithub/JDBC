基本思想ORM（object RelationShip mapping）

MySQL入手（基础知识）
注意：MySQL没有check约束
时间类型
   java.util.Data(父类)-----java.sql.Data(子类)
   Data:年月日
   time:时分秒
   timestamp:年月日时分秒
MySql基本语法
   mysql -hlocalhost -uroot -p123456-------------------------------------登录MySQL数据库
   
   create database 数据库名;------------------------------------------------创建数据库
   show databases;----------------------------------------------------查看有哪些数据库
   drop database 数据库名;--------------------------------------------------删除数据库
   use MySQL;---------------------------------------------------------------切换数据库
   
   show tables;-----------------------------------------------------------查看有哪些表
   show create table 表名;------------------------------------------------查看建表语句
   describe 表名;-----------------------------------------------------------查看表结构
   drop table 表名;-------------------------------------------------------------删除表
   create table student(
		     stuId int(11) not null,
		     stuName varchar(255) not null,
		     stuAge int(11) not null,
		     primary key(stuId)
   );---------------------------------------------------------------------------创建表
   alter table student add stuSex enum('男','女');--------------------------修改表结构
   alter table student add 
       foreign key(classId) references class(classId) on delete cascade;--给表添加外键
   
