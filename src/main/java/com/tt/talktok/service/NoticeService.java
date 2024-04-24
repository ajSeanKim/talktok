package com.tt.talktok.service;

import com.tt.talktok.dto.NoticeCreateDto;
import com.tt.talktok.entity.Notice;
import com.tt.talktok.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    //페이징
    public Page<Notice> getNoticePage(Pageable pageable) {

        return noticeRepository.findAll(pageable);
    }

    //저장
    public void write(NoticeCreateDto noticeCreateDto){

        Notice notice = Notice.builder()
                        .no_subject(noticeCreateDto.getNo_subject())
                        .no_content(noticeCreateDto.getNo_content())
                        .build();

        noticeRepository.save(notice);
    }
}
