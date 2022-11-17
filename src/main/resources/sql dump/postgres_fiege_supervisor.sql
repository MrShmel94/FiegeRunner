create table supervisor
(
    id          bigserial
        primary key,
    first_name  varchar(64) not null
        unique,
    last_name   varchar(64) not null
        unique,
    expertis    integer     not null
        unique,
    employee_id integer
        references employee
)
    using ???;

alter table supervisor
    owner to postgres;

