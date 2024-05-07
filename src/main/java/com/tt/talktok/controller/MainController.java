package com.tt.talktok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/main")
    public String mainRequest() {
        return "main";
    }
}
