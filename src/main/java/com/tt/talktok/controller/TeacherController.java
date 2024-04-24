package com.tt.talktok.controller;


import com.tt.talktok.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
}
