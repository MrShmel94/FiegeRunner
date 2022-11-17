create table employee
(
    id         bigserial
        primary key,
    expertis   integer     not null
        unique,
    password   varchar(64) not null,
    email      varchar(64)
        unique,
    first_name varchar(64) not null,
    last_name  varchar(64) not null,
    is_worked  boolean default true,
    team       varchar(64) not null,
    shift      varchar(4)  not null,
    department varchar(64) not null,
    role       varchar(64) not null,
    position   varchar(64) not null,
    comment    varchar(256),
    gender     varchar(64) not null
);

