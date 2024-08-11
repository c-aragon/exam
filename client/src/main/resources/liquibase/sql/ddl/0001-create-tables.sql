create table client (
    id_client long primary key auto_increment,
    name varchar(255),
    gender varchar(10),
    birth_date date,
    id_card varchar(18) unique,
    phone varchar(10),
    address varchar(255),
    client_identifier varchar(20) unique,
    password varchar(100),
    status varchar(10)
);

CREATE SEQUENCE client_seq MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 4 NOCACHE NOCYCLE;



