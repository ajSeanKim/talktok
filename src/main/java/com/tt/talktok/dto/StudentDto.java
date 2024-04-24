package com.tt.talktok.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Setter
@Getter
@AllArgsConstructor
public class StudentDto {

    private int stuNo;
    private String stuName;
    private String stuEmail;
    private String stuPwd;
    private String stuPhone;
    private String stuNickname;

    public StudentDto() {
        // 기본 생성자 추가
    }

}
