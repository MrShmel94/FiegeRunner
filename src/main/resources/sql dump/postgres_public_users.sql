create table users
(
    id        bigserial
        primary key,
    user_name varchar(128) not null
        unique,
    expertis  integer      not null
        unique,
    password  varchar(128) not null,
    role      varchar(64)  not null
);

INSERT INTO public.users (id, user_name, expertis, password, role) VALUES (1, 'test@gmail.ru', 304918, '{bcrypt}$2a$10$7o9DWHOtoa70e45rBT99FOrXzAfLCjesNaV.5hpJay5gh6r4lq0WG', 'Admin');
INSERT INTO public.users (id, user_name, expertis, password, role) VALUES (2, 'test2@gmail.ru', 124235, '{bcrypt}$2a$10$bNrNh2vbj5K8fr5fDo.UYeBoabVnkBWi9ropUi3w3hnBWqLzeBXl.', 'Storekeeper');
