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

 insert into reserva (ID_hospede,id_quarto, id_usuario, DTHR_CHECK_IN, DTHR_CHECK_OUT)
 	values (1, 1, 1, '2023-01-01', '2023-01-15');
    
 delete from reserva where id_quarto = 1;
 
select * from hospede;
select * from quarto;
select * from usuario;
select * from reserva;




    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
select * from quarto;    
select * from reserva;    
    
-- Período  para reservar: 04/06 a 10/06
select distinct(q.id_quarto) 
from quarto q, reserva r
where q.id_quarto = r.id_quarto
and r.dthr_check_in not between '2023-06-04' and '2023-06-10'
and r.dthr_check_out not between '2023-06-04' and '2023-06-10';

select distinct(quarto.id_quarto), quarto.* 
from quarto 
	left join reserva on reserva.id_quarto = quarto.id_quarto 
    and	(('2023-06-04' not between reserva.DTHR_CHECK_IN and reserva.DTHR_CHECK_OUT) 
	and ('2023-06-10' not between reserva.DTHR_CHECK_IN and reserva.DTHR_CHECK_OUT));    
    
    
    









    
