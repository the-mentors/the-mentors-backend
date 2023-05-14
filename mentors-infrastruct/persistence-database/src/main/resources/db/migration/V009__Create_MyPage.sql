create table if not exists mypages
(
    mypage_id    bigint PRIMARY KEY AUTO_INCREMENT,
    mentor_id    bigint   not null,
    mentee_id     bigint   not null,
    mentoring_id bigint   not null,
    created_at   datetime not null,
    updated_at   datetime not null
)engine = InnoDB;
ALTER TABLE mypages
    ADD FOREIGN KEY (mentoring_id) REFERENCES mentoring (mentoring_id);

ALTER TABLE mypages
    ADD FOREIGN KEY (mentee_id) REFERENCES users (user_id);

ALTER TABLE mypages
    ADD FOREIGN KEY (mentor_id) REFERENCES users (user_id);



