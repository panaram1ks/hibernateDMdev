DROP TABLE users;

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    firstname  varchar(128),
    lastname   varchar(128),
    birth_date date,
    username   varchar(128) UNIQUE,
    role       VARCHAR(32),
    info       jsonb,
    company_id INT REFERENCES company (id)
);

CREATE TABLE company
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) not null unique
);