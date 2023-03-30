create table if not exists users
(
    user_id     bigint PRIMARY KEY AUTO_INCREMENT,
    email       varchar(255) unique not null,
    password    varchar(255),
    username    varchar(255),
    nickname    varchar(14),
    profile_url varchar(255),
    role        varchar(255),
    created_at  datetime        not null,
    updated_at  datetime        not null
) engine = InnoDB;

create table if not exists auths
(
    user_id     bigint PRIMARY KEY AUTO_INCREMENT,
    refresh_token varchar(255) not null,
    created_at datetime not null,
    updated_at  datetime not null
) engine = InnoDB;

