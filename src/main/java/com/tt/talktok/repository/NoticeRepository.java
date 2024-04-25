package com.tt.talktok.repository;

import com.tt.talktok.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    //페이징 처리
    Page<Notice> findAll(Pageable pageable);
    //공지작성

}
