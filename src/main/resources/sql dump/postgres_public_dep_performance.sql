create table dep_performance
(
    id   bigserial
        primary key,
    date date         not null,
    name varchar(128) not null
        unique
);

