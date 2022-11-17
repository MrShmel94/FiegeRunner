create table evaluation
(
    id            bigserial
        primary key,
    employee_id   integer
        references employee,
    supervisor_id integer
        references employee,
    date          date not null,
    period        date not null,
    commitment    real not null,
    policies      real not null,
    cooperation   real not null
);
INSERT INTO public.evaluation (id, employee_id, supervisor_id, date, period, commitment, policies, cooperation) VALUES (1, 1, 2, '2022-10-09', '2022-01-10', 2.5, 3, 2.5);
INSERT INTO public.evaluation (id, employee_id, supervisor_id, date, period, commitment, policies, cooperation) VALUES (2, 1, 2, '2022-10-09', '2022-01-10', 2.5, 3, 2.5);
INSERT INTO public.evaluation (id, employee_id, supervisor_id, date, period, commitment, policies, cooperation) VALUES (3, 5, 2, '2022-10-09', '2022-01-10', 2.5, 3, 2.5);
