create table tb_book_purchases (
       purchase_id int8 not null,
       book_id int8 not null,
       constraint book_idFK foreign key (book_id) references tb_book ,
       constraint purchase_idFK foreign key (purchase_id) references tb_purchase
    );