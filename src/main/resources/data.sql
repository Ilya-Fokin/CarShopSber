insert into role(id, role) values ('a669579c-1551-4ed8-90ca-5acf7560f055', 'user'), ('ee9c6c1d-7617-4fba-a50c-2238cefc03c1', 'admin');

insert into users(id, username, first_name, second_name, mail, password, number_phone, role_id)
values ('6dcd66a1-e0b3-4b20-a2e0-1034ab5af02e', 'admin', 'Ilya', 'Fokin', 'fokin3349@mail.ru', 'admin', '89372583349', 'ee9c6c1d-7617-4fba-a50c-2238cefc03c1');