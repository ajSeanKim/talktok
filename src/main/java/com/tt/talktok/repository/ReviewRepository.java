package com.tt.talktok.repository;

import com.tt.talktok.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAll(Pageable pageable);

    Review findByRevNo(int rev_no);

    List<Review> findByTeaNo(int tea_no);

    Page<Review> findByStuNo(int stu_no, Pageable pageable);

    Page<Review> findByLecNameContaining(Pageable pageable, String keyword);

    Page<Review> findByTeaNameContaining(Pageable pageable, String keyword);

    Page<Review> findByRevNameContaining(Pageable pageable, String keyword);

    Page<Review> findByRevDetailContaining(Pageable pageable, String keyword);

    //Page<Review> findByRevNameContainingAndRevDetailContaining(Pageable pageable, String keyword);
}
