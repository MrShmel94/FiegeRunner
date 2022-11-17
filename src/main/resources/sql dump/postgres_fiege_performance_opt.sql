create table performance_opt
(
    id         bigserial
        primary key,
    expertis   integer not null,
    total_ql   integer not null,
    total_time integer not null,
    date       date    not null
)
    using ???;

alter table performance_opt
    owner to postgres;

