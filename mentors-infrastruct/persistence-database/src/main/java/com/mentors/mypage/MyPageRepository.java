package com.mentors.mypage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageRepository extends JpaRepository<MyPageEntity,Long> ,MyPageRepositoryQueryDsl {

}
