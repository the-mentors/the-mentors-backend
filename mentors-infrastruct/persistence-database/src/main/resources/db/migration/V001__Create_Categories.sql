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