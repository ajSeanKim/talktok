package com.tt.talktok.repository;

import com.tt.talktok.entity.Notice;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    //상세 및 페이징
    Page<Notice> findAll(Pageable pageable);

    Page<Notice> findByNoSubjectContaining(String keyword, Pageable pageable);

    //조회수 증가
    @Transactional
    @Modifying
    @Query(value = "update Notice n set n.noReadcount=n.noReadcount+1 where n.noNo=:noNo")
    void updateCount(@Param("noNo") Integer noNo);

}
