CREATE TABLE department (
                            id BIGSERIAL PRIMARY KEY ,
                            name VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO department (name)
VALUES ('SortPack'),
       ('PickStow'),
       ('Shipping'),
       ('Receive'),
       ('Administration'),
       ('Quality'),
       ('Unloading'),
       ('Rodzicielska'),
       ('Returns');

CREATE TABLE position (
                          id BIGSERIAL PRIMARY KEY ,
                          name VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO position (name)
VALUES ('Magazynier'),
       ('Mentor'),
       ('ProblemSolver'),
       ('TeamLead'),
       ('AreaManager'),
       ('FirstLvl'),
       ('WorkFlow'),
       ('Wozkowy'),
       ('Supporter'),
       ('SeniorProblemSolver');

CREATE TABLE team (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO team (name)
VALUES ('A'),
       ('B'),
       ('C'),
       ('D'),
       ('E'),
       ('F'),
       ('G'),
       ('H'),
       ('J'),
       ('N'),
       ('R');

CREATE TABLE shift (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL UNIQUE
    );

INSERT INTO shift (name)
VALUES ('A'),
       ('B'),
       ('N'),
       ('Rodzicielska');

CREATE TABLE users (
                      id BIGSERIAL PRIMARY KEY,
                      expertis INT NOT NULL UNIQUE ,
                      role VARCHAR(64) NOT NULL,
                      user_name VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL
);

CREATE TABLE employee (
                          id BIGSERIAL PRIMARY KEY,
                          first_name VARCHAR(255) NOT NULL,
                          last_name VARCHAR(255) NOT NULL,
                          date_of_hiring DATE,
                          date_of_dismissal DATE,
                          expertise INT NOT NULL UNIQUE,
                          supervisor_expertise INT,
                          department_id INT,
                          position_id INT NOT NULL,
                          is_worked  BOOLEAN NOT NULL DEFAULT TRUE,
                          comment VARCHAR(255) ,
                          gender  VARCHAR(64) NOT NULL,
                          shift_id INT,
                          team_id INT,
--                           FOREIGN KEY (supervisor_expertise) REFERENCES employee(expertise),
                          FOREIGN KEY (department_id) REFERENCES department(id),
                          FOREIGN KEY (position_id) REFERENCES position(id),
                          FOREIGN KEY (shift_id) REFERENCES shift(id),
                          FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE employee_team (
                               employee_id INT,
                               team_id INT,
                               PRIMARY KEY (employee_id, team_id),
                               FOREIGN KEY (employee_id) REFERENCES employee(id),
                               FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE ratings (
                         id BIGSERIAL PRIMARY KEY,
                         employee_id INT NOT NULL,
                         supervisor_id INT NOT NULL,
                         rating_param1 DECIMAL NOT NULL,
                         rating_param2 DECIMAL NOT NULL,
                         rating_param3 DECIMAL NOT NULL,
                         comment_param1 TEXT,
                         comment_param2 TEXT,
                         comment_param3 TEXT,
                         rating_date DATE NOT NULL,
                         FOREIGN KEY (employee_id) REFERENCES employee(id),
                         FOREIGN KEY (supervisor_id) REFERENCES employee(id)
);

CREATE TABLE processes (
                           id BIGSERIAL PRIMARY KEY,
                           process_name VARCHAR(255) NOT NULL UNIQUE,
                           process_need_performance INT NOT NULL
);

CREATE TABLE daily_logs (
                            id BIGSERIAL PRIMARY KEY,
                            employee_id INT,
                            process_id INT,
                            date DATE,
                            items_packed INT,
                            items_packed_boots INT,
                            time_spent INT,
                            FOREIGN KEY (employee_id) REFERENCES employee(id),
                            FOREIGN KEY (process_id) REFERENCES processes(id)
);

CREATE TABLE employee_shift (
                                employee_id INT,
                                shift_id INT,
                                PRIMARY KEY (employee_id, shift_id),
                                FOREIGN KEY (employee_id) REFERENCES employee(id),
                                FOREIGN KEY (shift_id) REFERENCES shift(id)
);

CREATE TABLE department_process (
                                    department_id INT,
                                    process_id INT,
                                    PRIMARY KEY (department_id, process_id),
                                    FOREIGN KEY (department_id) REFERENCES department(id),
                                    FOREIGN KEY (process_id) REFERENCES processes(id)
);

CREATE TABLE employee_attendance (
                                     id BIGSERIAL PRIMARY KEY,
                                     employee_id INT,
                                     date DATE,
                                     attendance_status VARCHAR(64) NOT NULL,
                                     FOREIGN KEY (employee_id) REFERENCES employee(id)
);