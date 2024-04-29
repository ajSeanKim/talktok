package com.tt.talktok.repository;

import com.tt.talktok.dto.ReviewDto;
import com.tt.talktok.entity.Lecture;
import com.tt.talktok.entity.Review;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAll(Pageable pageable);

    Review findByRevNo(int rev_no);

    List<Review> findByTeaNo(int tea_no);

    // 특정 학생이 작성한 후기 리스트를 학생 번호(stu_no)로 검색
    List<Review> findByStuNo(int stuNo);

}

