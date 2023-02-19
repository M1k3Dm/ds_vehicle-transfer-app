CREATE TABLE IF NOT exists users (
    username VARCHAR(25) PRIMARY KEY,
    email VARCHAR(40),
    first_name VARCHAR(25),
    last_name VARCHAR(25),
    password VARCHAR(100) not null,
    vat VARCHAR(9),
    enabled boolean not null
    );


CREATE TABLE IF NOT EXISTS authorities (
    username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,
    unique(username,authority),
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username));


INSERT INTO users (username, email, first_name, last_name, password, vat, enabled) VALUES
('admin', 'admin@hua.gr','', '', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC','000000000', 't'),
('user1', 'user1@hua.gr', 'First1', 'Last1', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', '012345678', 't'),
('user2', 'user2@hua.gr', 'First2', 'Last2', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', '123456789', 't'),
('user3', 'user3@hua.gr', 'First3', 'Last3', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', '234567890', 't');


INSERT INTO authorities(username, authority) VALUES
('admin', 'ROLE_ADMIN'),
('user1', 'ROLE_USER'),
('user2', 'ROLE_USER'),
('user3', 'ROLE_USER');

