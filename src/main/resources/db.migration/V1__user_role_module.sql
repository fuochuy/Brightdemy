CREATE TABLE IF NOT EXISTS role(
    role_id int,
    created_date timestamp,
    created_by varchar(36),
    modified_date timestamp,
    modified_by varchar(36),
    name varchar(255) not null unique,
    decription varchar(255),
    primary key (role_id)
    );

CREATE TABLE IF NOT EXISTS users(
     user_id int,
     created_date timestamp,
     created_by varchar(36),
    modified_date timestamp,
    modified_by varchar(36),
    name varchar(255) not null unique,
    email varchar(255) not null,
    password varchar(255) not null,
    status varchar(255) not null unique,
    role_id varchar(255) not null unique,
    primary key (user_id)
    );