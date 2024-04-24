package com.tt.talktok.service;

import com.tt.talktok.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository TeacherRepository;
}
