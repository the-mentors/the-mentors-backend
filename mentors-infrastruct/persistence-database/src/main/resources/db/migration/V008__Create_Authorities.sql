create table if not exists AUTHORITIES
(
    authority_id bigint PRIMARY KEY AUTO_INCREMENT,
    role         varchar(255)
) engine = InnoDB;

create table user_roles
(
    user_id      bigint not null,
    authority_id bigint not null,
    primary key (user_id, authority_id)
)