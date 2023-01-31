create table banks
(
    bank_id         varchar(36) primary key,
    country         varchar,
    postal_code     varchar,
    city            varchar,
    street          varchar,
    building_number varchar
);

create table customers
(
    customer_id     varchar(36) primary key,
    bank_id         varchar(36),
    first_name      varchar,
    last_name       varchar,
    country         varchar,
    postal_code     varchar,
    city            varchar,
    street          varchar,
    building_number varchar,
    FOREIGN KEY (bank_id) REFERENCES banks (bank_id)
);

insert into banks(bank_id, country, postal_code, city, street, building_number)
values ('111', 'bank 111 country', 'bank 111 postal_code', 'bank 111 city', 'bank 111 street', 'bank 111 building_number');



create table customer_assignment_to_bank
(
    customer_id     varchar(36) primary key,
    first_name      varchar,
    last_name       varchar,
    bank_country         varchar,
    bank_postal_code     varchar,
    bank_city            varchar,
    bank_street          varchar,
    bank_building_number varchar
);

