package com.tt.talktok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view")
public class ViewController {
    @GetMapping("/loginStuIntersection")
    public String stuLogin() {
        return "loginStuIntersection";
    }

    @GetMapping("/loginTeaIntersection")
    public String teaLogin() {
        return "loginTeaIntersection";
    }

    @GetMapping("/joinStuIntersection")
    public String stuJoin() {
        return "joinStuIntersection";
    }

    @GetMapping("/joinTeaIntersection")
    public String teaJoin() {
        return "joinTeaIntersection";
    }
}
