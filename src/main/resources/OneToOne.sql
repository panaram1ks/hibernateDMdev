CREATE TABLE company
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) not null unique
);

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    firstname  varchar(128),
    lastname   varchar(128),
    birth_date date,
    username   varchar(128) UNIQUE,
    role       VARCHAR(32),
    info       jsonb,
--     company_id INT REFERENCES company (id)
    company_id INT REFERENCES company (id) ON DELETE CASCADE
);

CREATE TABLE profile
(
    user_id BIGINT PRIMARY KEY REFERENCES users (id),
    street VARCHAR(128),
    language CHAR(2)
);

CREATE TABLE profile
(
    id BIGSERIAL PRIMARY KEY,
    user_id  BIGINT not null unique REFERENCES users (id),
    street   VARCHAR(128),
    language CHAR(2)
);

drop table profile;