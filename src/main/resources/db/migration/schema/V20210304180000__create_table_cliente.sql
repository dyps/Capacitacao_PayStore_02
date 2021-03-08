  create table tb_client (
       id int8 not null,
        age int4,
        email varchar(255),
        name varchar(255),
        sex varchar(255),
        telephone varchar(255),
        primary key (id)
    );
create sequence client_seq start 1 increment 1