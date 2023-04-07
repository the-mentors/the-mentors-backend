create table if not exists mentoring_links
(
    mentoring_link_id    bigint PRIMARY KEY AUTO_INCREMENT,
    mentoring_id bigint not null,
    link_type varchar(255) not null,
    link_url varchar(255) not null,
    created_at      datetime not null,
    updated_at      datetime not null
) engine = InnoDB;

ALTER TABLE mentoring_links
    ADD FOREIGN KEY (mentoring_id) REFERENCES mentoring (mentoring_id) ON DELETE CASCADE;