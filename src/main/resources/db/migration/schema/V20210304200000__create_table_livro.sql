create table tb_livro (
       id int8 not null,
        ano_de_publicacao date,
        autor varchar(255),
        isbn varchar(255),
        preco_para_venda float4,
        quantidade_disponivel int4,
        sinopse varchar(255),
        titulo varchar(255),
        primary key (id)
    );
create sequence livro_seq start 1 increment 1