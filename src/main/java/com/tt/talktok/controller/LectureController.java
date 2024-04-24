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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("/list")
    public String list(Model model,@PageableDefault(size = 1) Pageable pageable) {
        Page<LectureDto> lectureList = lectureService.findAll(pageable);
        model.addAttribute("lectureList",lectureList);
        return "/lecture/list";
    }
    @GetMapping("/detail")
    public String detail(@RequestParam("no") int lec_no, Model model) {
        LectureDto lectureDto = lectureService.findLectureByLecNo(lec_no);
        model.addAttribute("lectureDto",lectureDto);
        return "/lecture/detail";
    }
}
