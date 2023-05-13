create table if not exists reivew_mentoring
(
    reivew_mentoring_id   bigint PRIMARY KEY AUTO_INCREMENT,
    mentoring_id bigint unique not null,
    rating_average double,
    total_rating double,
    one_count int,
    two_count int,
    three_count int,
    fout_count int,
    five_count int,
    total_count int,
    created_at      datetime not null,
    updated_at      datetime not null
) engine = InnoDB;