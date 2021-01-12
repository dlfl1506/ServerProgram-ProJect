# 서버프로그램 구현 

## 환경

- windows10
- jdk1.8
- tomcat9.0
- sts tool
- mysql8.0
- lombok
- gson (json파싱)
- 인코딩 utf-8

## MySQL 데이터베이스 생성 및 사용자 생성

```sql
create user 'project'@'%' identified by '1234';
GRANT ALL privileges ON *.* TO 'project'@'%';
create database Serverproject;
```

## MySQL 테이블 생성
- project 사용자로 접속
- use serverproject; 데이터베이스 선택

```sql
create table user(
id int primary key auto_increment,
    username varchar(30) not null unique,
    password varchar(50) not null,
    email varchar(50) not null,
    role varchar(20)
);
```
