create table tb_livro_categorias_livro (
       livros_id int8 not null,
       categorias_livro_id int8 not null,
       constraint categoreia_id_fk foreign key (categorias_livro_id) references tb_categoria_livro,
       constraint livros_id_fk foreign key (livros_id) references tb_livro
    );