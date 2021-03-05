 create table tb_livro_compras (
       compra_id int8 not null,
       livro_id int8 not null,
       constraint livro_id_fk foreign key (livro_id) references tb_livro,
       constraint compra_id_fk foreign key (compra_id) references tb_compra
    );