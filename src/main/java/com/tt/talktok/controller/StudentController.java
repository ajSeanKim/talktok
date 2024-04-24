package com.tt.talktok.controller;

import com.tt.talktok.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
}
