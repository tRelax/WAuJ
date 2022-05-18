-- create table if not exists hardware_type (
--   id bigint primary key auto_increment,
--   name varchar(15) not null
-- );
create table if not exists user (
    id identity,
    username varchar(100) not null unique,
    password varchar(1000) not null
);

create table if not exists authority (
    id identity,
    authority_name varchar(100) not null unique
);

create table if not exists user_authority (
    user_id bigint not null,
    authority_id bigint not null,
    constraint fk_user foreign key (user_id) references user(id),
    constraint fk_authority foreign key (authority_id) references authority(id)
);

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
    content varchar(255) not null,
    rating tinyint(1) not null,
    hardware_id int not null,
    FOREIGN KEY (hardware_id) REFERENCES hardware(id) on delete cascade
);