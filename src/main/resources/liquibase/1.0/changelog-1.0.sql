CREATE TABLE Users
(
    user_id       SERIAL PRIMARY KEY,
    username      VARCHAR(255),
    email_address VARCHAR(255),
    password      VARCHAR(255) CHECK (LENGTH(password) >= 6 AND password SIMILAR TO '%[0-9a-zA-Z]%')
);