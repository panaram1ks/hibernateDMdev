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