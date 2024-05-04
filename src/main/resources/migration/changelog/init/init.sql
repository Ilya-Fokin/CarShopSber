insert into roles
values ('a669579c-1551-4ed8-90ca-5acf7560f055', 'ROLE_USER'),
       ('ee9c6c1d-7617-4fba-a50c-2238cefc03c1', 'ROLE_ADMIN');

insert into users
values ('40c16258-0e51-451e-8a0e-7020bcf47180', 'Ilya', 'fokin3349@mail.ru', '89372583349',
        '$2a$10$t.14GbETanp.KSOhNaF7SemiJ1J0TN0CXoN2Krz1WzgblNxEDxXd6', 'Fokin', 'admin');

insert into users_roles values ('40c16258-0e51-451e-8a0e-7020bcf47180', 'ee9c6c1d-7617-4fba-a50c-2238cefc03c1');