package com.tt.talktok.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study")
public class StudyController {

    @GetMapping("/room")
    public String room(HttpSession session) {
        String email = (String) session.getAttribute("stuEmail");

        return "study/videoroomtest";
    }
}
