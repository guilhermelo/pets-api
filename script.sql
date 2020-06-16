
create database petsapi;

use petsapi;

CREATE TABLE customers(
    id VARCHAR(15) primary key,
    name VARCHAR(100),
    address VARCHAR(100),
    document VARCHAR(20),
    plan VARCHAR(10),
    monthly_payment NUMERIC(10)
);

CREATE TABLE pets(
    id VARCHAR(15) primary key,
    name VARCHAR(50),
    breed VARCHAR(30),
    age VARCHAR(30),
    owner VARCHAR(15),
    foreign key (owner) references customer(id)
);

CREATE TABLE users(
    id VARCHAR(40) primary key,
    name VARCHAR(50),
    email VARCHAR(50),
    username VARCHAR(20),
    password VARCHAR(100)
);
