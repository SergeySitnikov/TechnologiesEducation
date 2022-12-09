CREATE TABLE IF NOT EXISTS user
(
    id    BIGINT PRIMARY KEY ,
    username  VARCHAR(200) NOT NULL ,
    password VARCHAR(254) NOT NULL ,
    status VARCHAR(25) NOT NULL
);
CREATE TABLE IF NOT EXISTS role
(
    id    BIGINT PRIMARY KEY ,
    name  VARCHAR(25) NOT NULL ,
);
CREATE TABLE IF NOT EXISTS user_roles
(
    FOREIGN KEY (user_id) REFERENCES user id,
    FOREIGN KEY (role_id) REFERENCES role id,
);