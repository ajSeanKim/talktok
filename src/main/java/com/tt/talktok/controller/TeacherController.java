package com.tt.talktok.controller;

import com.tt.talktok.dto.ReviewDto;
import com.tt.talktok.dto.StudentDto;
import com.tt.talktok.dto.TeacherDto;
import com.tt.talktok.entity.Teacher;
import com.tt.talktok.service.TeacherService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
@Slf4j
public class TeacherController {
    private final BCryptPasswordEncoder passwordEncoder;
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

/*----------------------------------------------------------------------------------*/
    //비밀번호 변경
    @GetMapping("pwdUpdate")
    public String pwdUpdate() {
        return "teacher/pwdUpdateForm";
    }
    @PostMapping("pwdUpdate")
    public String pwdUpdate(TeacherDto teacherDto, @RequestParam("teaNewPwd") String teaNewPwd, Model model, HttpSession session) {
        int teaNo = (int) session.getAttribute("teaNo");
        TeacherDto dbTeacher = teacherService.findTeacher(teaNo);
        int result = 0;
        // 현재 비밀번호 확인
        if (passwordEncoder.matches(teacherDto.getTeaPwd(), dbTeacher.getTeaPwd())) {

            TeacherDto newTeacher = new TeacherDto();
            newTeacher.setTeaPwd((teacherDto.getTeaPwd()));
            String encpassword = passwordEncoder.encode(teaNewPwd);
            newTeacher.setTeaPwd(encpassword);
            result = 1;
//            result = studentService.updatePwd(newStudent);

        }else {
            result=-1;
        }
        System.out.println(result);
        model.addAttribute("result", result);
        return "teacher/pwdUpdate";
    }

    // 회원정보 수정폼
    @GetMapping("/update")
    public String update(HttpSession session, Model model) {
        int teaNo = (int) session.getAttribute("teaNo");
        TeacherDto teacherDto = teacherService.findTeacher(teaNo);
        model.addAttribute("teacherDto", teacherDto);
        return "teacher/updateForm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute TeacherDto teacherDto, HttpSession session, Model model) throws Exception {
        int teaNo = (int) session.getAttribute("teaNo");
        teacherDto.setTeaNo(teaNo);

        TeacherDto dbTeacher = this.teacherService.findTeacher(teaNo);
        // student update
        if(passwordEncoder.matches(teacherDto.getTeaPwd(), dbTeacher.getTeaPwd())) {
            teacherDto.setTeaPwd(dbTeacher.getTeaPwd());
            System.out.println("수정완료");
            return "redirect:/teacher/myPage"; // 정보 업데이트 후 마이페이지로 리다이렉트
        } else{ //비밀번호 불일치
            return "teacher/updateForm";
        }
    }
}