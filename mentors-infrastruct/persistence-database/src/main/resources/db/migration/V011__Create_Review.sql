create table if not exists reviews
(
    reviews_id   bigint PRIMARY KEY AUTO_INCREMENT,
    reviewer_id bigint not null,
    mentoring_id bigint,
    rating        varchar(255),
    content         LONGTEXT NOT NULL,
    created_at      datetime not null,
    updated_at      datetime not null
) engine = InnoDB;


ALTER TABLE reviews
    ADD FOREIGN KEY (reviewer_id) REFERENCES users (user_id);