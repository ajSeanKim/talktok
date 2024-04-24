package com.tt.talktok.service;

import com.tt.talktok.dto.TeacherDto;
import com.tt.talktok.entity.Teacher;
import com.tt.talktok.repository.TeacherRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    // DTO에서 엔터티로 변환하는 메서드
    public Teacher convertToEntity(TeacherDto dto) {
        return Teacher.builder()
                .tea_no(dto.getTea_no())
                .tea_name(dto.getTea_name())
                .tea_email(dto.getTea_email())
                .tea_phone(dto.getTea_phone())
                .tea_nickname(dto.getTea_nickname())
                .tea_intro(dto.getTea_intro())
                .tea_detail(dto.getTea_detail())
                .tea_career(dto.getTea_career())
                .tea_nation(dto.getTea_nation())
                .build();
    }

    // 엔터티에서 DTO로 변환하는 메서드
    public TeacherDto convertToDto(Teacher entity) {
        return TeacherDto.builder()
                .tea_no(entity.getTea_no())
                .tea_name(entity.getTea_name())
                .tea_email(entity.getTea_email())
                .tea_phone(entity.getTea_phone())
                .tea_nickname(entity.getTea_nickname())
                .tea_intro(entity.getTea_intro())
                .tea_detail(entity.getTea_detail())
                .tea_career(entity.getTea_career())
                .tea_nation(entity.getTea_nation())
                .build();
    }


    //선생 목록 조회
    public List<TeacherDto> list(){
        List<Teacher> teacherlists = teacherRepository.findAll();
        return teacherlists.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //선생 상세페이지 조회
    public Teacher getTeacherDetail(Integer teacher_no){
        return teacherRepository.findById(teacher_no).orElse(null);
    }
}
