create table if not exists mentoring_hashtags
(
    mentoring_hashtag_id    bigint PRIMARY KEY AUTO_INCREMENT,
    hashtag_id      bigint not null,
    mentoring_id    bigint not null,
    created_at      datetime not null,
    updated_at      datetime not null
) engine = InnoDB;

ALTER TABLE mentoring_hashtags
    ADD FOREIGN KEY (mentoring_id) REFERENCES mentoring (mentoring_id) ON DELETE CASCADE;

ALTER TABLE mentoring_hashtags
    ADD FOREIGN KEY (hashtag_id) REFERENCES hashtags (hashtag_id);