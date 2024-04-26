package com.tt.talktok.controller;

import com.tt.talktok.dto.ReviewDto;
import com.tt.talktok.dto.TeacherDto;
import com.tt.talktok.entity.Teacher;
import com.tt.talktok.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
@Slf4j
public class TeacherController {

    private final TeacherService teacherService;

    // 선생님 목록 조회
    @GetMapping("/list")
    public String list(
                        @PageableDefault(page=0, size = 6,direction = Sort.Direction.DESC) Pageable pageable,
                        @RequestParam(required = false, name = "searchTutorName") String keyword,
                        Model model) {
        Page<TeacherDto> list = null;
        if (keyword != null && !keyword.isEmpty()) {
            list = teacherService.teacherSearchList(keyword, pageable);
        }else{
            list = teacherService.teacherList(pageable);
        }
        System.out.println("keyword:" + keyword);
        model.addAttribute("teacherList", list);

        return "teacher/list";
    }

    @GetMapping("/detail")
    public String teacherDetail(@RequestParam("teaNo") int tea_no, Model model) {

        System.out.println("tea_no" + tea_no);
        TeacherDto teacherDetail = teacherService.getTeacherDetail(tea_no);
        model.addAttribute("teacherDetail", teacherDetail);

        return "teacher/detail";
    }
}