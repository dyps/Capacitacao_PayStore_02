create table tb_compra (
       id int8 not null,
        concluida boolean,
        data_da_compra date,
        valor_total float4,
        cliente_id int8,
        primary key (id)
    )