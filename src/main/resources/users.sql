CREATE TABLE users
(
    username varchar(128) PRIMARY KEY ,
    firstname varchar(128),
    lastname varchar(128),
    birth_date date,
    age INT
);

ALTER TABLE users
    ADD role INT ;

ALTER TABLE users
    ADD role INT ;

ALTER TABLE users
    DROP COLUMN role;

ALTER TABLE users
    ADD COLUMN role varchar(32);

DROP TABLE users;

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    username   varchar(128) UNIQUE ,
    firstname  varchar(128),
    lastname   varchar(128),
    birth_date date,
    role       VARCHAR(32),
    info       jsonb
);

CREATE SEQUENCE user_id_seq
    owned by users.id;


DROP SEQUENCE user_id_seq;
CREATE TABLE all_sequence
(
    table_name VARCHAR(32) PRIMARY KEY ,
    pk_value BIGINT NOT NULL
);