create table tb_compra (
       id int8 not null,
       concluida boolean,
       data_da_compra date,
       valor_total float4,
       cliente_id int8,
       primary key (id),
       constraint cliente_id_fk foreign key (cliente_id) references tb_cliente(id)
    );
create sequence compra_seq start 1 increment 1