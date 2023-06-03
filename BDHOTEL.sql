DROP DATABASE IF EXISTS DBHOTEL;
CREATE DATABASE DBHOTEL;

USE DBHOTEL;

CREATE TABLE HOSPEDE(
ID_HOSPEDE INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
NOME VARCHAR (255) NOT NULL,
CPF VARCHAR(11),
ENDERECO VARCHAR(255),
TELEFONE VARCHAR(13)
);

CREATE TABLE USUARIO(
ID_USUARIO INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
NOME VARCHAR (255) NOT NULL,
CPF VARCHAR(11),
-- ENDERECO VARCHAR(255), -- NÃO VEJO NESCECIDADE
TELEFONE VARCHAR(13),
PERFIL ENUM("GERENTE", "RECEPCIONISTA")
);

CREATE TABLE QUARTO(
ID_QUARTO INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
NUMERO INT NOT NULL,
VALOR_DIARIA DECIMAL(8,2),
TIPO_QUARTO ENUM("BÁSICO", "INTERMEDIÁRIO", "LUXO")
);

CREATE TABLE RESERVA(
ID_RESERVA INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
ID_HOSPEDE INT,
ID_QUARTO INT,
ID_USUARIO INT,
DTHR_CHECK_IN DATETIME,
DTHR_CHECK_OUT DATETIME,
FOREIGN KEY (ID_HOSPEDE) REFERENCES HOSPEDE (ID_HOSPEDE),
FOREIGN KEY (ID_QUARTO) REFERENCES QUARTO (ID_QUARTO),
FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO (ID_USUARIO)
);