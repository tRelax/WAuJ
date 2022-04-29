create table if not exists hardwares (
    id bigint primary key auto_increment,
    code varchar(20) unique not null,
    name varchar(255) not null,
    price double not null,
    type varchar(15) not null,
    available integer not null
);