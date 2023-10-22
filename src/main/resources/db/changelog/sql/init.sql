CREATE TABLE city
(
    id   uuid         NOT NULL PRIMARY KEY,
    name varchar(200) NOT NULL
);

CREATE TABLE weather_type
(
    id   uuid         NOT NULL PRIMARY KEY,
    name varchar(200) NOT NULL
);

CREATE TABLE weather
(
    id          uuid      NOT NULL PRIMARY KEY,
    temperature integer   NOT NULL,
    date_time   timestamp NOT NULL,
    type_id     uuid      NOT NULL REFERENCES weather_type (id),
    city_id     uuid      NOT NULL REFERENCES city (id)
);

CREATE INDEX IF NOT EXISTS city_i1 ON city (name);
CREATE INDEX IF NOT EXISTS weather_i1 ON weather (date_time, city_id);

INSERT INTO weather_type(id, name)
VALUES ('5ce5672a-c90c-4830-8743-5e0fddee2cbd', 'snowy'),
       ('3bee7c70-95a5-439d-acc0-0a8c1cd10d2d', 'rainy'),
       ('1ef7b938-4f5d-46a6-903f-4e70e07d5105', 'sunny'),
       ('7f2699de-b79f-4841-9354-9103ba8c60c9', 'cloudy');

INSERT INTO city(id, name)
VALUES ('a3e58d73-038c-427b-a82a-4e0f7d49570d', 'Moscow'),
       ('3766eebe-b262-4ad0-bb0f-7e22b2c827e2', 'Tver'),
       ('6ef75edd-ac8a-4c2d-8b18-d4744eb89847', 'Rostov'),
       ('6f645260-3fcb-404b-8720-66fbeca3ce26', 'Saint-Petersburg'),
       ('204cbc4c-e4da-4d71-865f-87ac5c06110d', 'Yekaterinburg');