create table account (
    id_account long primary key auto_increment,
    account_type varchar(10) not null,
    balance numeric not null,
    status varchar(10) not null,
    id_client long not null
);

create table transaction (
    id_transaction long primary key auto_increment,
    id_account long not null,
    date datetime not null,
    amount numeric not null,
    balance numeric not null,
    old_balance numeric not null,
    transaction_type varchar(10),
    status varchar(10) not null
);

CREATE SEQUENCE account_seq MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 5 NOCACHE NOCYCLE;

CREATE SEQUENCE transaction_seq MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 NOCACHE NOCYCLE;



