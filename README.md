# CS313 - DBMS Labs



## Installation 


**How to Postgresql on Ubuntu**


***Postgresql Installation***
```
$ sudo apt-get update
 
$ sudo apt install postgresql postgresql-contrib
```
---


***Use Default User-psql***
```
$ sudo -u postgres psql postgres
```
---


***Use New User***

```
$ sudo -u postgres psql postgres

postgres=# CREATE ROLE universitydb0015 WITH PASSWORD '123';

postgres=# ALTER ROLE  "universitydb0015" WITH LOGIN;


// for existing university db

$ psql -U universitydb0015 -h localhost -p 5432 -d university;

(Add tables through sql file)

university=> \i /home/"username"/Downloads/DDL.sql
```
---


# Postgresql 


***list of databases***

```
university=> \l
```

***create database***

```
university=> CREATE DATABASE dbname;
```

***drop databases***

```
university=> DROP DATABASE dbname;
```


***to connect databases***

```
university=> \c university;
```
---

***list of tables***

```
university=> \dt
```
***create table***

```
university=> CREATE TABLE table_name(
   ID INT PRIMARY KEY     NOT NULL,
   NAME           TEXT    NOT NULL,
   AGE            INT     NOT NULL,
   ADDRESS        CHAR(50),
   SALARY         REAL
);
```

***drop table***

```
university=> DROP TABLE table_name;
```

***info related table***

```
university=> \d student;

OR 

university=> SELECT 
   table_name, 
   column_name, 
   data_type 
FROM 
   information_schema.columns
WHERE 
   table_name = 'student';

```
---

