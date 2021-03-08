    create table tb_book_book_categories (
       books_id int8 not null,
       book_categories_id int8 not null,
       constraint book_categories_idFK foreign key (book_categories_id) references tb_book_category,
       constraint books_idFK foreign key (books_id) references tb_book
    );
    
