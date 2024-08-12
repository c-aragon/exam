create table report (
    id_report long primary key auto_increment,
    start_date DATETIME not null ,
    end_date DATETIME not null ,
    id_client long not null ,
    report_status varchar(15),
    name varchar(100),
    data clob
);

CREATE SEQUENCE report_seq MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 NOCACHE NOCYCLE;



