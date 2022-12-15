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
CREATE sequence hibernate_sequence start 4 increment 1;
insert into role(role_id,name,description)
values(0,'USER','USER'),
      (1,'ADMIN','ADMIN'),
      (2,'OWNER','OWNER');
insert into users(user_id, username,full_name,email, password,status,role_id)
values(3,'admin','adminn','admin@brightdemy.com','$2a$10$R5hPELUnSGIhzcn5kodVSeBIOZuPxx4Ixy9V3wfJJR5i56yHaPXZm',0,1);