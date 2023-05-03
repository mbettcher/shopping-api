create schema if not exists shoppings;

create table shoppings.shop (
    id bigserial primary key,
    user_identifier varchar(100) not null,
    date timestamp not null,
    total float not null
);

create table shoppings.item (
    shop_id bigserial REFERENCES shoppings.shop(id),
    product_identifier varchar(100) not null,
    price float not null
);