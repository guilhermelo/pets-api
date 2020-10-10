drop database petsapi;
create database petsapi;

use petsapi;

CREATE TABLE customers(
    id VARCHAR(40) primary key,
    name VARCHAR(100),
    address VARCHAR(100),
    document VARCHAR(20),
    plan VARCHAR(10),
    monthly_payment NUMERIC(10)
);

CREATE TABLE pets(
    id VARCHAR(40) primary key,
    name VARCHAR(50),
    race VARCHAR(30),
    age VARCHAR(30),
    owner VARCHAR(40),
    foreign key (owner) references customers(id)
);

CREATE TABLE users(
    id VARCHAR(40) primary key,
    name VARCHAR(50),
    email VARCHAR(50),
    username VARCHAR(20),
    password VARCHAR(100)
);

CREATE TABLE activities(
    id VARCHAR(40) primary key,
    description VARCHAR(100),
    date DATETIME,
    pet VARCHAR(40),
    type VARCHAR(20),
    foreign key (pet) references pets(id)
);