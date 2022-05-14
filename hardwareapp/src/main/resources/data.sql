
delete from review;
delete from hardware;

insert into hardware (code, name, price, type, available)
    values ('001', 'AMD Ryzen 7 5800X', 4220.00, 'CPU', 1);
insert into hardware (code, name, price, type, available)
    values ('002', 'Palit GeForce RTX 2060 Dual 12GB GDDR6', 5554.44, 'GPU', 8);
insert into hardware (code, name, price, type, available)
    values ('003', 'Other Test Object', 1000.00, 'OTHER', 101);

insert into review(title, content, score, hardware_id)
values ( 'amd1', 'dosta dobro', 3, 1 );

insert into review(title, content, score, hardware_id)
values ( 'amd2', 'bio sam vrlo pijan kad sam ovo kupio i sada trijezan shvacam kako je to lose', 1, 1 );

insert into review(title, content, score, hardware_id)
values ( 'palit1', 'samo meh', 2, 2 );

insert into review(title, content, score, hardware_id)
values ( 'palit2', 'ovo je najbolja stvar koju sam ikad imao preporucujem!!!!!', 5, 2 );

insert into review(title, content, score, hardware_id)
values ( 'other1', 'wauwweve, nez ovo je samo eh', 3, 3 );

insert into review(title, content, score, hardware_id)
values ( 'other2', 'najgora stvar koju imam', 1, 3 );