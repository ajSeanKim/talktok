package com.tt.talktok.controller;

import com.tt.talktok.dto.ReviewDto;
import com.tt.talktok.dto.StudentDto;
import com.tt.talktok.dto.TeacherDto;
import com.tt.talktok.entity.Review;
import com.tt.talktok.entity.Teacher;
import com.tt.talktok.service.ReviewService;
import com.tt.talktok.service.TeacherService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
@Slf4j
public class TeacherController {



    @Value("${spring.mail.hostSMTPid}")
    String hostSMTPid;

    @Value("${spring.mail.hostSMTPpwd}")
    String hostSMTPpwd;

    private final TeacherService teacherService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ReviewService reviewService;

    // teacher 목록 조회
    @GetMapping("/list")
    public String list(
                        @PageableDefault(page=0, size = 12,direction = Sort.Direction.DESC) Pageable pageable,
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

    //Teacher 상세페이지
    @GetMapping("/detail")
    public String teacherDetail(@RequestParam("teaNo") int tea_no, Model model) {

        System.out.println("tea_no" + tea_no);
        //상세페이지
        TeacherDto teacherDetail = teacherService.getTeacherDetail(tea_no);
        //후기글
        List<ReviewDto> reviews = reviewService.reviewFindTeacher(tea_no);

        model.addAttribute("reviews", reviews);
        model.addAttribute("teacherDetail", teacherDetail);

        return "teacher/detail";
    }

    //teacher 로그인
    @GetMapping("/login")
    public String login() {
        return "teacher/loginForm";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute TeacherDto teacher, Model model, HttpSession session) {
        int result = 0;
        System.out.println(teacher.getTeaPwd());
        String email = teacher.getTeaEmail();
        TeacherDto dbTeacher = teacherService.findTeacher(email);

        // 등록되지 않은 선생님의 경우 - dbTeacher로 null 비교를 할경우 entity에서 dto로 주소를 옮기며 주소값이 들어가 무조건 null이 아니게 됩니다.
        if(dbTeacher.getTeaEmail() == null){

            System.out.println("등록되지 않은 선생님의 경우");
            model.addAttribute("result", result);
            // 등록된 선생님의 경우
        } else {
            //비번이 같을때
            if(passwordEncoder.matches(teacher.getTeaPwd(), dbTeacher.getTeaPwd())){
                result = 1;

                System.out.println("비번이 같을때");
                session.setAttribute("teaEmail", email);
                model.addAttribute("result", result);
                //비번이 다를때
            } else {
                System.out.println("비번이 다를때");
                result = 2;
                model.addAttribute("result", result);
            }
        }
        return "teacher/login";
    }


    @GetMapping("/join")
    public String join() {
        return "teacher/joinForm";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute TeacherDto teacher, Model model) {
        int result = 0;

        String teaEmail = teacher.getTeaEmail();

        TeacherDto dbTeacher = teacherService.findTeacher(teaEmail);
        //가입된 email = 1, 가입안된 email = 0

            teacherService.join(teacher);
            model.addAttribute("result",result);
        return "teacher/join";
    }

    // 아이디 중복검사(ajax 리턴)
    @PostMapping("/idCheck")
    @ResponseBody
    public int idcheck(@RequestParam("teaEmail") String teaEmail) {
        int result = 0;

        TeacherDto teacher = teacherService.findTeacher(teaEmail);
        if (teacher.getTeaEmail() != null) { // 중복 ID
            result = 1;
        } else { // 사용가능 ID
            result = -1;
        }

        return result;
    }


    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "teacher/logout";
    }

    //마이페이지
    @GetMapping("/myPage")
    public String myPage() {
        return "teacher/myPage";
    }

    @GetMapping("/findPwd")
    public String findPwd(){
        return "teacher/findPwdForm";
    }


    @PostMapping("/findPwd")
    public String findPwd(@ModelAttribute TeacherDto teacherDto, Model model) {
        int result = 0;

        String teaEmail = teacherDto.getTeaEmail();
        TeacherDto dbTeacher = teacherService.findTeacher(teaEmail);
        if (dbTeacher.getTeaEmail() == null) {
            model.addAttribute("result",result);
            return "teacher/findPwd";

        } else {
            // 비밀번호를 업데이트 할 dto 객체를 생성
            TeacherDto teacher = new TeacherDto();
            //비밀번호를 난수화하여 db 에 저장
            Random random = new Random();
            String newPwd = String.valueOf(random.nextInt(999999));
            teacher.setTeaEmail(teaEmail);
            teacher.setTeaPwd(passwordEncoder.encode(newPwd));
            teacherService.updatePwd(teacher);

            // Mail Server 설정
            String charSet = "utf-8";
            String hostSMTP = "smtp.naver.com";


            // 보내는 사람 EMail, 제목, 내용
            String fromEmail = "aircamp03@naver.com";
            String fromName = "관리자";
            String subject = "비밀번호 찾기";

            // 받는 사람 E-Mail 주소 : 원래는 받는 사람도 직접 작성했지만, 여기서는 DB에 저장된 정보가 존재 해서 그걸 불러서 사용한다.

            try {
                HtmlEmail email = new HtmlEmail();
                email.setDebug(true);
                email.setCharset(charSet);
                email.setSSL(true);
                email.setHostName(hostSMTP);
                email.setSmtpPort(587);

                email.setAuthentication(hostSMTPid, hostSMTPpwd);
                email.setTLS(true);
                email.addTo(teaEmail, charSet);
                email.setFrom(fromEmail, fromName, charSet);
                email.setSubject(subject);
                email.setHtmlMsg("<p align = 'center'>비밀번호 찾기</p><br>" +
                        "<div align='center'> 임시 비밀번호:"+newPwd+"</div>");
                email.send();
            } catch (Exception e) {
                System.out.println(e);
            }

            result = 1;
            model.addAttribute("result",result);

            return "teacher/findPwd";

        }
    }

    // 회원 탈퇴 양식으로 이동
    @GetMapping("/withdraw")
    public String withdraw() {
        return "teacher/withdrawForm";
    }


    // 회원탈퇴
    @PostMapping("/withdraw")
    public String withdraw(@ModelAttribute TeacherDto teacherDto, HttpSession session, Model model) {
        int result=0;

        String teaEmail = (String) session.getAttribute("teaEmail");

        TeacherDto dbTeacher = teacherService.findTeacher(teaEmail);

        String rawPwd = teacherDto.getTeaPwd();

        //비밀번호 일치시 회원탈퇴.
        if (passwordEncoder.matches(rawPwd, dbTeacher.getTeaPwd())) {
            teacherService.withdraw(teaEmail);
            session.invalidate();

            result=1;
            model.addAttribute("result",result);
            return "teacher/withdraw";
            //비밀번호 불일치시
        } else {
            model.addAttribute("result",result);
            return "teacher/withdraw";
        }

    }





}