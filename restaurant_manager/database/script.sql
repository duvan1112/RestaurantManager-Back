--Creación de la base de datos y tablas
--Software I
--DBMS: MySQL
--Julio 03, 2022

--Creación base de datos
CREATE DATABASE restaurant_manager;

--Creación usuario 
CREATE USER 'restaurantManager'@'localhost' IDENTIFIED BY '@RestaurantManager123';
GRANT ALL PRIVILEGES ON restaurant_manager . * TO 'restaurantManager'@'localhost';
ALTER USER 'restaurantManager'@'localhost' IDENTIFIED WITH mysql_native_password BY '@RestaurantManager123';

FLUSH PRIVILEGES;

--Usar base de datos
USE inscriptions_db;

insert into roles (roles.name)values("ROLE_USER");
insert into roles (roles.name)values("ROLE_ADMIN");
insert into roles (roles.name)values("ROLE_WORKER");