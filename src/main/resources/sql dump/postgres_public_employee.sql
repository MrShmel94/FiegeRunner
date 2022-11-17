create table employee
(
    id                  bigserial
        primary key,
    expertis            integer      not null
        unique,
    first_name          varchar(256) not null,
    last_name           varchar(256) not null,
    is_worked           boolean default true,
    team                varchar(8)   not null,
    shift               varchar(8)   not null,
    department          varchar(64)  not null,
    position            varchar(128) not null,
    comment             varchar(512),
    gender              varchar(32)  not null,
    supervisor_expertis integer      not null,
    employment          varchar(32)  not null
);


INSERT INTO public.employee (id, expertis, first_name, last_name, is_worked, team, shift, department, position, comment, gender, supervisor_expertis, employment) VALUES (2, 304192, 'Pepa', 'Knopka', true, 'E', 'A', 'SortPack', 'Magazynie', null, 'FEMALE', 1, 'FIEGE');
INSERT INTO public.employee (id, expertis, first_name, last_name, is_worked, team, shift, department, position, comment, gender, supervisor_expertis, employment) VALUES (3, 302158, 'Pipa', 'Chuipa', true, 'E', 'A', 'SortPack', 'Magaz', null, 'FEMALE', 1, 'FIEGE');
INSERT INTO public.employee (id, expertis, first_name, last_name, is_worked, team, shift, department, position, comment, gender, supervisor_expertis, employment) VALUES (1, 304573, 'Baran', 'Patrycja', true, 'E', 'A', 'SortPack', 'TeamLead', null, 'FEMALE', 1, 'FIEGE');
INSERT INTO public.employee (id, expertis, first_name, last_name, is_worked, team, shift, department, position, comment, gender, supervisor_expertis, employment) VALUES (4, 304918, 'Vlad', 'Shumchenia', true, 'E', 'A', 'SortPack', 'TeamLead', null, 'MALE', 1, 'FIEGE');
INSERT INTO public.employee (id, expertis, first_name, last_name, is_worked, team, shift, department, position, comment, gender, supervisor_expertis, employment) VALUES (5, 304917, 'Stela', 'Shumhcenia', true, 'E', 'A', 'SortPack', 'Magazynier', null, 'FEMALE', 1, 'FIEGE');

