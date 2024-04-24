package com.tt.talktok.controller;

import com.tt.talktok.dto.StudentDto;
import com.tt.talktok.service.StudentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    @Value("${spring.mail.hostSMTPid}")
    String hostSMTPid;

    @Value("${spring.mail.hostSMTPpwd}")
    String hostSMTPpwd;

    private final BCryptPasswordEncoder passwordEncoder;
    private final StudentService studentService;

    @GetMapping("/login")
    public String login() {
        return "student/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute StudentDto student, Model model, HttpSession session) {
        int result = 0;
        System.out.println(student.getStuPwd());
        String email = student.getStuEmail();
        StudentDto dbStudent = studentService.findStudent(email);

        // 등록되지 않은 학생의 경우
        if(dbStudent.getStuEmail() == null){

            System.out.println("등록되지 않은 학생의 경우");
            model.addAttribute("result", result);
            // 등록된 학생의 경우
        } else {
            //비번이 같을때
            if(passwordEncoder.matches(student.getStuPwd(), dbStudent.getStuPwd())){
                result = 1;

                System.out.println("비번이 같을때");
                session.setAttribute("stuEmail", email);
                model.addAttribute("result", result);
            //비번이 다를때
            } else {
                System.out.println("비번이 다를때");
                result = 2;
                model.addAttribute("result", result);
            }
        }
        return "student/login";
    }

    @GetMapping("/join")
    public String join() {
        return "student/joinForm";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute StudentDto student, Model model) {
        int result = 0;

        String stuEmail = student.getStuEmail();

         StudentDto dbStudent = studentService.findStudent(stuEmail);
        //가입된 email = 1, 가입안된 email = 0
        if(dbStudent != null){
            studentService.join(student);
            model.addAttribute("result",result);
            return "student/join";
        }
        return "student/join";
    }


    // 아이디 중복검사(ajax 리턴)
    @PostMapping("/idCheck")
    @ResponseBody
    public int idcheck(@RequestParam("stuEmail") String stuEmail) {
        int result = 0;

        StudentDto student = studentService.findStudent(stuEmail);
        if (student.getStuEmail() != null) { // 중복 ID
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

        return "student/logout";
    }

    //마이페이지
    @GetMapping("/myPage")
    public String myPage() {
        return "student/myPage";
    }

    @GetMapping("/findPwd")
    public String findPwd(){
        return "student/findPwdForm";
    }

    @PostMapping("/findPwd")
    public String findPwd(@ModelAttribute StudentDto studentDto, Model model) {
        int result = 0;

        String stuEmail = studentDto.getStuEmail();
        StudentDto dbStudent = studentService.findStudent(stuEmail);
        if (dbStudent == null) {
            model.addAttribute("result",result);
            return "stduent/findPwd";

        } else {
            // 비밀번호를 업데이트 할 dto 객체를 생성
            StudentDto student = new StudentDto();
            //비밀번호를 난수화하여 db 에 저장
            Random random = new Random();
            String newPwd = String.valueOf(random.nextInt(999999));
            student.setStuEmail(stuEmail);
            student.setStuPwd(passwordEncoder.encode(newPwd));
            studentService.updatePwd(student);

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
                email.addTo(stuEmail, charSet);
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

            return "student/findPwd";

        }
    }

    // 회원 탈퇴 양식으로 이동
    @GetMapping("/withdraw")
    public String withdraw() {
        return "student/withdrawForm";
    }


    // 회원탈퇴
    @PostMapping("/withdraw")
    public String withdraw(@ModelAttribute StudentDto studentDto, HttpSession session, Model model) {
        int result=0;

        String stuEmail = (String) session.getAttribute("stuEmail");

        StudentDto dbStudent = studentService.findStudent(stuEmail);

        String rawPwd = studentDto.getStuPwd();

        //비밀번호 일치시 회원탈퇴.
        if (passwordEncoder.matches(rawPwd, dbStudent.getStuPwd())) {
            studentService.withdraw(stuEmail);
            session.invalidate();

            result=1;
            model.addAttribute("result",result);
            return "student/withdraw";
            //비밀번호 불일치시
        } else {
            model.addAttribute("result",result);
            return "student/withdraw";
        }

    }
}
