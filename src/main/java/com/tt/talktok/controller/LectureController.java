package com.tt.talktok.controller;

import com.tt.talktok.dto.LectureDto;
import com.tt.talktok.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("/list")
    public String list(Model model,@PageableDefault(page = 0, size = 4) Pageable pageable) {
        Page<LectureDto> lectureList = lectureService.findAll(pageable);
        int currentPage = lectureList.getNumber(); // 현재 페이지 번호 가져가기(1번부터 시작하기)
        model.addAttribute("lectureList",lectureList);
        model.addAttribute("currentPage",currentPage);
        return "/lecture/list";
    }
    @GetMapping("/detail")
    public String detail(@RequestParam("no") int lec_no, @RequestParam("page") int currentPage, Model model) {
        LectureDto lectureDto = lectureService.findLectureByLecNo(lec_no);
        int page = currentPage;
        model.addAttribute("lectureDto",lectureDto);
        model.addAttribute("page",page);
        return "/lecture/detail";
    }

}
