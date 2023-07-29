DROP DATABASE IF EXISTS ecommerce;

CREATE DATABASE ecommerce;

CREATE TABLE users (id int PRIMARY KEY NOT NULL auto_increment,first_name varchar(45) NOT NULL,last_name varchar(45) NOT NULL,email varchar(45) NOT NULL,password varchar(50) NOT NULL,phone varchar(15) NOT NULL,birth_date varchar(20) NOT NULL,gender varchar(10) NOT NULL,type varchar (10));

CREATE TABLE addresses (id int PRIMARY KEY NOT NULL auto_increment,address varchar(200) NOT NULL,landmark varchar(30) NOT NULL,pin varchar(10) NOT NULL,user_id int NOT NULL, FOREIGN KEY (user_id) REFERENCES users(id)); 

CREATE TABLE categories (id int PRIMARY KEY NOT NULL auto_increment, description varchar(200) NOT NULL , name varchar(100) NOT NULL);

CREATE TABLE products (id int PRIMARY KEY NOT NULL auto_increment, description varchar(500) NOT NULL,name varchar(150) NOT NULL,category_id int not null, FOREIGN KEY (category_id) REFERENCES categories(id));

CREATE TABLE product_inventory (id int PRIMARY KEY NOT NULL auto_increment, quantity int NOT NULL, product_id int not null, FOREIGN KEY (product_id) REFERENCES products(id));

CREATE TABLE product_images (id int PRIMARY KEY NOT NULL auto_increment, url text NOT NULL, product_id int not null, FOREIGN KEY (product_id) REFERENCES products(id));

CREATE TABLE reviews (id int PRIMARY KEY NOT NULL auto_increment, review text NOT NULL, product_id int not null,user_id int NOT NULL,rating int not null, FOREIGN KEY (product_id) REFERENCES products(id), FOREIGN KEY (user_id) REFERENCES  users(id));

ALTER TABLE products ADD rating int;

ALTER TABLE products ADD brand varchar(50) NOT NULL;

ALTER TABLE products ADD unit_price int NOT NULL;

CREATE TABLE carts (id int PRIMARY KEY NOT NULL auto_increment , user_id int not null, cart_total int default 0,status varchar(10), FOREIGN KEY (user_id) REFERENCES  users(id));

CREATE TABLE cart_items (id int PRIMARY KEY NOT NULL auto_increment, total float not null,product_id int not null,quantity int not null,cart_id int not null, FOREIGN KEY (product_id) REFERENCES products(id) , FOREIGN KEY (cart_id) REFERENCES carts(id)); 