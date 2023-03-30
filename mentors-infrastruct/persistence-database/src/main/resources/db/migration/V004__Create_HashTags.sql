create table if not exists hashtags
(
    hashtag_id     bigint PRIMARY KEY AUTO_INCREMENT,
    hashtag_name varchar(50) not null,
    created_at datetime not null,
    updated_at  datetime not null
) engine = InnoDB;