DROP DATABASE IF EXISTS ecommerce;

CREATE DATABASE ecommerce;

CREATE TABLE users (id int PRIMARY KEY NOT NULL auto_increment,first_name varchar(45) NOT NULL,last_name varchar(45) NOT NULL,email varchar(45) NOT NULL,password varchar(50) NOT NULL,phone varchar(15) NOT NULL,birth_date varchar(20) NOT NULL,gender varchar(10) NOT NULL,type varchar (10));