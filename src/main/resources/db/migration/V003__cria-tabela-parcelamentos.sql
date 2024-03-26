create table parcelamento(
                             id bigint not null auto_increment,
                             cliente_id bigint not null,
                             descricao varchar(255) not null,
                             valor decimal(10,2) not null,
                             quantidade_parcelas tinyint not null,
                             data_criacao datetime not null,

                             primary key(id)
);

alter table parcelamento add constraint fk_parcelamento_cliente foreign key(cliente_id) references cliente(id);
