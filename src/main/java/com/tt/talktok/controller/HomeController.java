package com.tt.talktok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    //공지사항 만들다가 만든거라 지워도 됨니다.
    @RequestMapping("/")
    public String Main(){
        return "main";
    }



}
