create table if not exists mentoring_categories
(
    mentoring_category_id    bigint PRIMARY KEY AUTO_INCREMENT,
    category_id bigint not null,
    mentoring_id bigint not null,
    created_at      datetime not null,
    updated_at      datetime not null
) engine = InnoDB;

ALTER TABLE mentoring_categories
    ADD FOREIGN KEY (mentoring_id) REFERENCES mentoring (mentoring_id) ON DELETE CASCADE;

ALTER TABLE mentoring_categories
    ADD FOREIGN KEY (category_id) REFERENCES categories (category_id);