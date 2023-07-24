DROP DATABASE IF EXISTS ecommerce;

CREATE DATABASE ecommerce;

CREATE TABLE users (id int PRIMARY KEY NOT NULL auto_increment,first_name varchar(45) NOT NULL,last_name varchar(45) NOT NULL,email varchar(45) NOT NULL,password varchar(50) NOT NULL,phone varchar(15) NOT NULL,birth_date varchar(20) NOT NULL,gender varchar(10) NOT NULL,type varchar (10));

CREATE TABLE addresses (id int PRIMARY KEY NOT NULL auto_increment,address varchar(200) NOT NULL,landmark varchar(30) NOT NULL,pin varchar(10) NOT NULL,user_id int NOT NULL,FOREIGN KEY (user_id) REFERENCES users(id)); 

CREATE TABLE categories (id int PRIMARY KEY NOT NULL auto_increment, description varchar(200) NOT NULL , name varchar(100) NOT NULL);

CREATE TABLE products (id int PRIMARY KEY NOT NULL auto_increment, description varchar(500) NOT NULL,name varchar(150) NOT NULL,category_id int not null,FOREIGN KEY (category_id) REFERENCES categories(id));

CREATE TABLE product_inventory (id int PRIMARY KEY NOT NULL auto_increment,quantity int NOT NULL,name varchar(150) NOT NULL,category_id int not null,FOREIGN KEY (category_id) REFERENCES categories(id));   