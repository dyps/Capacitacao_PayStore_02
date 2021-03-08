  create table tb_purchase (
       id int8 not null,
        amount float4,
        completed boolean,
        date_purchase date,
        client_id int8,
        primary key (id),
        constraint client_idFK foreign key (client_id) references tb_client
    );
create sequence purchase_seq start 1 increment 1