CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    firstname  varchar(128),
    lastname   varchar(128),
    birth_date date,
    username   varchar(128) UNIQUE,
    role       VARCHAR(32),
    info       jsonb,
    company_id INT REFERENCES company (id) ON DELETE CASCADE
);

CREATE TABLE chat
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE users_chat
(
    user_id BIGINT REFERENCES users (id),
    chat_id BIGINT REFERENCES chat (id),
    PRIMARY KEY (user_id, chat_id)
);
