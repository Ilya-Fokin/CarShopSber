
CREATE TABLE public.car (
                            mileage integer NOT NULL,
                            price integer NOT NULL,
                            id character varying(36) NOT NULL,
                            user_id character varying(36),
                            brand character varying(255) NOT NULL,
                            color character varying(255) NOT NULL,
                            description character varying(255) NOT NULL,
                            model character varying(255) NOT NULL,
                            title character varying(255) NOT NULL,
                            transmission character varying(255) NOT NULL,
                            type character varying(255) NOT NULL,
                            wheel_drive character varying(255) NOT NULL,
                            year_of_release character varying(255) NOT NULL,
                            CONSTRAINT car_mileage_check CHECK ((mileage >= 1)),
                            CONSTRAINT car_price_check CHECK ((price >= 1)),
                            CONSTRAINT car_transmission_check CHECK (((transmission)::text = ANY ((ARRAY['AUTOMATIC'::character varying, 'ROBOT'::character varying, 'VARIATOR'::character varying, 'MECHANICAL'::character varying])::text[]))),
                            CONSTRAINT car_type_check CHECK (((type)::text = ANY ((ARRAY['SEDAN'::character varying, 'OFF_ROAD'::character varying, 'WAGON'::character varying])::text[]))),
                            CONSTRAINT car_wheel_drive_check CHECK (((wheel_drive)::text = ANY ((ARRAY['FRONT_WHEEL_DRIVE'::character varying, 'FOUR_WHEEL_DRIVE'::character varying, 'REAR_WHEEL_DRIVE'::character varying])::text[])))
);
CREATE TABLE public.car_images (
                                   car_id character varying(36),
                                   id character varying(36) NOT NULL,
                                   path character varying(255) NOT NULL
);


CREATE TABLE public.favourite (
                                  id character varying(36) NOT NULL,
                                  user_id character varying(36) NOT NULL
);


CREATE TABLE public.favourite_cars (
                                       car_id character varying(36) NOT NULL,
                                       favourite_id character varying(36) NOT NULL
);


CREATE TABLE public.roles (
                              id character varying(36) NOT NULL,
                              name character varying(255) NOT NULL
);

CREATE TABLE public.users (
                              id character varying(36) NOT NULL,
                              first_name character varying(255),
                              mail character varying(255),
                              number_phone character varying(255),
                              password character varying(255),
                              second_name character varying(255),
                              username character varying(255)
);



CREATE TABLE public.users_roles (
                                    role_id character varying(36) NOT NULL,
                                    user_id character varying(36) NOT NULL
);

ALTER TABLE ONLY public.car_images
    ADD CONSTRAINT car_images_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.favourite
    ADD CONSTRAINT favourite_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.favourite
    ADD CONSTRAINT favourite_user_id_key UNIQUE (user_id);

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (role_id, user_id);


ALTER TABLE ONLY public.favourite_cars
    ADD CONSTRAINT favourite_id FOREIGN KEY (favourite_id) REFERENCES public.favourite(id);

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_role FOREIGN KEY (user_id) REFERENCES public.users(id);


ALTER TABLE ONLY public.car
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES public.users(id);


ALTER TABLE ONLY public.favourite_cars
    ADD CONSTRAINT car_id FOREIGN KEY (car_id) REFERENCES public.car(id);


ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT role_id FOREIGN KEY (role_id) REFERENCES public.roles(id);

ALTER TABLE ONLY public.car_images
    ADD CONSTRAINT car_images_id FOREIGN KEY (car_id) REFERENCES public.car(id);


ALTER TABLE ONLY public.favourite
    ADD CONSTRAINT favourite_user_id FOREIGN KEY (user_id) REFERENCES public.users(id);


insert into public.roles
values ('a669579c-1551-4ed8-90ca-5acf7560f055', 'ROLE_USER'),
       ('ee9c6c1d-7617-4fba-a50c-2238cefc03c1', 'ROLE_ADMIN');

insert into public.users
values ('40c16258-0e51-451e-8a0e-7020bcf47180', 'Ilya', 'fokin3349@mail.ru', '89372583349',
        '$2a$10$t.14GbETanp.KSOhNaF7SemiJ1J0TN0CXoN2Krz1WzgblNxEDxXd6', 'Fokin', 'admin');

insert into public.users_roles values ('ee9c6c1d-7617-4fba-a50c-2238cefc03c1', '40c16258-0e51-451e-8a0e-7020bcf47180');
