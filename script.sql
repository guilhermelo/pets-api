
create database petsapi;

CREATE TABLE pets(
    id VARCHAR(15) primary key,
    nome VARCHAR(30),
    bairro VARCHAR(30),
    rua VARCHAR(30),
    numero VARCHAR(5),
    dono VARCHAR(50)
);
