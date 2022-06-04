create table if not exists hardware (
    id long auto_increment not null,
    code varchar(20) unique not null,
    name varchar(255) not null,
    price double not null,
    type varchar(15) not null,
    available integer not null,
    primary key (id)
);
create table if not exists review(
    id long auto_increment not null,
    title varchar(255) not null,
    content varchar(255) not null,
    rating int not null,
    hardware_id long,
    primary key (id),
    foreign key (hardware_id) references hardware(id) on delete cascade
);

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