
package com.tt.talktok.service;

import com.tt.talktok.dto.StudentDto;
import com.tt.talktok.dto.TeacherDto;
import com.tt.talktok.entity.Student;
import com.tt.talktok.entity.Teacher;
import com.tt.talktok.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Builder
@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // DTO에서 엔터티로 변환하는 메서드
    public Teacher convertToEntity(TeacherDto dto) {
        return Teacher.builder()
                .tea_no(dto.getTeaNo())
                .teaName(dto.getTeaName())
                .teaEmail(dto.getTeaEmail())
                .tea_pwd(dto.getTeaPwd())
                .tea_phone(dto.getTeaPhone())
                .tea_nickname(dto.getTeaNickname())
                .tea_account(dto.getTeaAccount())
                .tea_intro(dto.getTeaIntro())
                .tea_detail(dto.getTeaDetail())
                .tea_career(dto.getTeaCareer())
                .tea_nation(dto.getTeaNation())
                .tea_image(dto.getTeaImage())
                .tea_social(dto.getTeaSocial())
                .build();
    }

    // 엔터티에서 DTO로 변환하는 메서드
    public TeacherDto convertToDto(Teacher entity) {
        return TeacherDto.builder()
                .teaNo(entity.getTea_no())
                .teaName(entity.getTeaName())
                .teaEmail(entity.getTeaEmail())
                .teaPwd(entity.getTea_pwd())
                .teaPhone(entity.getTea_phone())
                .teaNickname(entity.getTea_nickname())
                .teaAccount(entity.getTea_account())
                .teaIntro(entity.getTea_intro())
                .teaDetail(entity.getTea_detail())
                .teaCareer(entity.getTea_career())
                .teaNation(entity.getTea_nation())
                .teaImage(entity.getTea_image())
                .teaSocial(entity.getTea_social())
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

    public TeacherDto findTeacher(String teaEmail) {
        System.out.println("서비스 이동");
        Teacher dbTeacher = teacherRepository.findTeacherByTeaEmail(teaEmail);
        TeacherDto dbTeacherDto = new TeacherDto();
        if(dbTeacher !=null) {
            dbTeacherDto=convertToDto(dbTeacher);
        }
        return dbTeacherDto;
    }


    public void join(TeacherDto teacherDto) {
        Teacher newTeacher = new Teacher();

        String pwd=teacherDto.getTeaPwd();
        String encodePwd = passwordEncoder.encode(pwd);

        newTeacher = convertToEntity(teacherDto);
        newTeacher.setTea_pwd(encodePwd);

        teacherRepository.save(newTeacher);
    }

    @Transactional
    public void withdraw(String teaEmail) {
        teacherRepository.deleteTeacherByTeaEmail(teaEmail);
    }

    public void updatePwd(TeacherDto teacherDto) {
        String teaEmail = teacherDto.getTeaEmail();
        Teacher newTeacher = teacherRepository.findTeacherByTeaEmail(teaEmail);

        newTeacher.setTea_pwd(teacherDto.getTeaPwd());

        teacherRepository.save(newTeacher);


    }


}

