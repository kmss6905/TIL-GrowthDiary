create table coupon(
                       id int auto_increment,
                       name varchar(255) not null,
                       issuer int not null,
                       primary key (id),
                       unique (issuer)
);

create table user(
                     id int auto_increment,
                     name varchar(255),
                     primary key (id)
);

create table issued_coupon(
                              id int auto_increment,
                              issuer int not null ,
                              owner int not null ,
                              created_at timestamp not null default now(),
                              primary key (id)
);
