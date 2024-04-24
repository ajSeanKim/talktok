package com.tt.talktok.controller;

import com.tt.talktok.dto.NoticeCreateDto;
import com.tt.talktok.entity.Notice;
import com.tt.talktok.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/list") //아무 설정 없이 @PageableDefault 어노테이션을 사용시, default 설정으로 페이징 기법이 발생.
    public String noticeList(@PageableDefault(page = 0, size = 10) Pageable pageable, Model model){

        Page<Notice> list = noticeService.getNoticePage(pageable);

        model.addAttribute("list", list);

        return "notice/notice";
    }

    // 공지사항 작성폼으로 이동
    @GetMapping("/write")
    public String noticeWrite() {

        return "notice/write";
    }

    // 공지사항 작성후 db 저장
    @PostMapping("/write")
    public String noticeWrite(NoticeCreateDto noticeCreateDto) {

        noticeService.write(noticeCreateDto);

        return "redirect:list";
    }

    // 공지사항 상세 정보 출력
//    @GetMapping("/detail")
//    public String noticeDetail(@RequestParam("no_no") int no_no, Search search, Model model) {
//
//        noticeService.noticeReadcount(no_no);
//        Notice noticeOne = noticeService.getNoticeOne(no_no);
//        String no_content = noticeOne.getNo_content().replace("\n","<br>");
//        noticeOne.setNo_content(no_content);
//
//        model.addAttribute("notice", noticeOne);
//        model.addAttribute("search", search);
//
//        return "notice/detail";
//
//    }
}
