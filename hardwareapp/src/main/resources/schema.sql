create table if not exists hardwares (
    code identity,
    name varchar(255) not null,
    price double not null,
    type varchar(15) not null,
    available integer not null
);