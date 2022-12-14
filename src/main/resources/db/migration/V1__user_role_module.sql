CREATE TABLE IF NOT EXISTS role(
    role_id int,
    created_date timestamp,
    created_by varchar(36),
    modified_date timestamp,
    modified_by varchar(36),
    name varchar(255) not null unique,
    description varchar(255),
    primary key (role_id)
    );

CREATE TABLE IF NOT EXISTS users(
     user_id int,
     created_date timestamp,
     created_by varchar(36),
    modified_date timestamp,
    modified_by varchar(36),
    username varchar(255) not null unique,
    full_name varchar(255),
    email varchar(255) not null,
    password varchar(255) not null,
    status int,
    role_id int,
    primary key (user_id)
    );
CREATE sequence hibernate_sequence start 1 increment 1;
