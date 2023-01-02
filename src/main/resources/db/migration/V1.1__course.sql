CREATE TABLE course(
    course_id int,
    name varchar(255),
    description varchar(255),
    owner_id int,
    status int,
    open_time timestamp,
    training_time timestamp,
    language varchar(255),
    framework varchar(255),
    position varchar(255),
    created_date timestamp,
    created_by varchar(36),
    modified_date timestamp,
    modified_by varchar(36),

    primary key (course_id)
    );

CREATE TABLE users_course(
    id int,
    course_id int,
    user_id int,
    role varchar(255),
    status int,
    training_time timestamp,
    created_date timestamp,
    created_by varchar(36),
    modified_date timestamp,
    modified_by varchar(36),

    primary key (id)
    );