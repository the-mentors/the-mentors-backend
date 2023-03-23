create table if not exists categories
(
    category_id   bigint PRIMARY KEY AUTO_INCREMENT,
    category_code bigint unique      not null,
    category_name varchar(50) unique not null,
    parent_code   bigint             not null,
    created_at    datetime           not null,
    updated_at    datetime           not null
) engine = InnoDB;

create index CATEGORIES__index_category_code
    on categories (category_code);


create table if not exists users
(
    user_id     bigint PRIMARY KEY AUTO_INCREMENT,
    email       varchar(255) unique not null,
    password    varchar(255),
    username    varchar(255),
    nickname    varchar(14),
    profile_url varchar(255),
    role        varchar(255),
    created_at  timestamp(6)        not null,
    updated_at  timestamp(6)        not null
) engine = InnoDB;