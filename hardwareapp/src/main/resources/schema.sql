-- create table if not exists hardware_type (
--   id bigint primary key auto_increment,
--   name varchar(15) not null
-- );

create table if not exists hardware (
    id bigint primary key auto_increment,
    code varchar(20) unique not null,
    name varchar(255) not null,
    price double not null,
    type varchar(15) not null,
    available integer not null
);
create table if not exists review(
    id bigint primary key auto_increment,
    title varchar(255) not null,
    score tinyint(1) not null,
    hardware_id int not null,
    FOREIGN KEY (hardware_id) REFERENCES hardware(id) on delete cascade
);