drop table if exists cliente;
create table cliente (
    id bigint not null primary key auto_increment,
    nome varchar(60) not null,
    email varchar(255) not null,
    telefone varchar(20) not null
);

alter table cliente add constraint uk_cliente unique (email);

insert into cliente (nome, email, telefone) values ('Fulano', 'email@tiringa.com', '123456789');
insert into cliente (nome, email, telefone) values ('Ciclano', 'email@toronga.com', '124556789');
insert into cliente (nome, email, telefone) values ('PaunoCu', 'email@Avaula.com', '123456089');