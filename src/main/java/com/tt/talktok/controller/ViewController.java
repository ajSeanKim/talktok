package com.tt.talktok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view")
public class ViewController {
    @GetMapping("/loginIntersection")
    public String showLogin() {
        return "/loginIntersection";
    }

    @GetMapping("/joinIntersection")
    public String showJoin() {
        return "/joinIntersection";
    }
}
