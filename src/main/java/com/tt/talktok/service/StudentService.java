package com.tt.talktok.service;

import com.tt.talktok.dto.StudentDto;
import com.tt.talktok.dto.TeacherDto;
import com.tt.talktok.entity.Student;
import com.tt.talktok.repository.StudentRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Builder
@Service
@RequiredArgsConstructor
public class StudentService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    //entity의 내용을 dto로 이동
    public StudentDto convertToDto(Student entity){
        return StudentDto.builder()
                .stuNo(entity.getStuNo())
                .stuName(entity.getStuName())
                .stuEmail(entity.getStuEmail())
                .stuPwd(entity.getStuPwd())
                .stuPhone(entity.getStuPhone())
                .stuNickname(entity.getStuNickname())
                .build();
    }

    //entity의 내용을 dto로 이동
    public Student convertToEntity(StudentDto dto){
        return Student.builder()
                .stuNo(dto.getStuNo())
                .stuName(dto.getStuName())
                .stuEmail(dto.getStuEmail())
                .stuPwd(dto.getStuPwd())
                .stuPhone(dto.getStuPhone())
                .stuNickname(dto.getStuNickname())
                .build();
    }


    public StudentDto findStudent(String stuEmail) {

        Student dbStudent = studentRepository.findStudentByStuEmail(stuEmail);
        StudentDto dbStudentDto = new StudentDto();
        if(dbStudent !=null) {
            dbStudentDto=convertToDto(dbStudent);
        }
        return dbStudentDto;
    }

    public void join(StudentDto studentDto) {
        Student newStudent = new Student();

        String pwd=studentDto.getStuPwd();
        String encodePwd = passwordEncoder.encode(pwd);

        newStudent = convertToEntity(studentDto);
        newStudent.setStuPwd(encodePwd);

        studentRepository.save(newStudent);
    }

    public void withdraw(String stuEmail) {
        Student dbStudent = studentRepository.findStudentByStuEmail(stuEmail);
        studentRepository.delete(dbStudent);
    }

    public void updatePwd(StudentDto studentDto) {
        String stuEmail = studentDto.getStuEmail();
        Student newStudent = studentRepository.findStudentByStuEmail(stuEmail);

        newStudent.setStuPwd(studentDto.getStuPwd());

        studentRepository.save(newStudent);


    }


}



