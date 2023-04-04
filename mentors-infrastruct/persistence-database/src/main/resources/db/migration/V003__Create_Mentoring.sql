create table if not exists mentoring
(
    mentoring_id    bigint PRIMARY KEY AUTO_INCREMENT,
    user_id         bigint not null,
    content         LONGTEXT NOT NULL,
    price           int not null,
    thumbnail       varchar(255) not null,
    title           varchar(50) not null,
    created_at      datetime not null,
    updated_at      datetime not null
) engine = InnoDB;

ALTER TABLE mentoring
    ADD FOREIGN KEY (user_id) REFERENCES users (user_id);