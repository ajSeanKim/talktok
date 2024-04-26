package com.tt.talktok.service;

import com.tt.talktok.dto.TeacherDto;
import com.tt.talktok.entity.Teacher;
import com.tt.talktok.repository.TeacherRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Builder
@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    // DTO에서 엔터티로 변환하는 메서드
    public Teacher convertToEntity(TeacherDto dto) {
        return Teacher.builder()
                .tea_no(dto.getTeaNo())
                .teaName(dto.getTeaName())
                .teaEmail(dto.getTeaEmail())
                .tea_phone(dto.getTeaPhone())
                .tea_nickname(dto.getTeaNickname())
                .tea_intro(dto.getTeaIntro())
                .tea_detail(dto.getTeaDetail())
                .tea_career(dto.getTeaCareer())
                .tea_nation(dto.getTeaNation())
                .build();
    }

    // 엔터티에서 DTO로 변환하는 메서드
    public TeacherDto convertToDto(Teacher entity) {
        return TeacherDto.builder()
                .teaNo(entity.getTea_no())
                .teaName(entity.getTeaName())
                .teaEmail(entity.getTeaEmail())
                .teaPhone(entity.getTea_phone())
                .teaNickname(entity.getTea_nickname())
                .teaIntro(entity.getTea_intro())
                .teaDetail(entity.getTea_detail())
                .teaCareer(entity.getTea_career())
                .teaNation(entity.getTea_nation())
                .build();
    }

    //선생 목록 조회
    public Page<TeacherDto> teacherList(Pageable pageable){
        Page<Teacher> teachers = teacherRepository.findAll(pageable);
        return teachers.map(this::convertToDto);
    }
    //선생 검색 기능
    public Page<TeacherDto> teacherSearchList(String keyword, Pageable pageable){
        Page<Teacher> teacherlists = teacherRepository.findByTeaNameContaining(keyword,pageable);
        return teacherlists.map(this::convertToDto);
    }

    //선생 상세페이지 조회
    public TeacherDto getTeacherDetail(int tea_no){
        Teacher teacherdetails = teacherRepository.findById(tea_no).orElse(null);
        return convertToDto(teacherdetails);
    }
}
