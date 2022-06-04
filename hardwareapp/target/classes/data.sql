delete from review;
delete from hardware;
delete from user_authority;
delete from authority;
delete from user;

insert into user(id, username, password)
    values
        (1, 'user', '$2a$12$qIfhnfF8PHVNHsHpgaE1y.dfklY/cp4fbsic.J3/zq5U4zfDPeE0C'), -- password = user
        (2, 'admin', '$2a$12$OR9SeNM.1txwlt7IUemGJ.MUhPe3wp2kCRrWtuY/wJ9gQ9D.GkhuO'); -- password = admin
insert into authority (id, authority_name)
    values
        (1, 'ROLE_ADMIN'),
        (2, 'ROLE_USER');
insert into user_authority (user_id, authority_id)
    values
        (1, 2),
        (2, 1);

insert into hardware (code, name, price, type, available)
    values
        ('001', 'AMD Ryzen 7 5800X', 4220.00, 'CPU', 1),
        ('002', 'Palit GeForce RTX 2060 Dual 12GB GDDR6', 5554.44, 'GPU', 8),
        ('003', 'Other Test Object', 1000.00, 'OTHER', 101);

insert into review(title, content, rating, hardware_id)
values
    ('amd1', 'dosta dobro', 3, 1),
    ('amd2', 'bio sam vrlo pijan', 1, 1),
    ('palit1', 'samo meh', 2, 2),
    ('palit2', 'ovo je najbolja stvar koju', 5, 2),
    ('other1', 'wauwweve, nez ovo je samo eh', 3, 3),
    ('other2', 'najgora stvar koju imam', 1, 3);