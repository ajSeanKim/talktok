package com.tt.talktok.dto;

import com.tt.talktok.entity.Teacher;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class TeacherDto {
    public int tea_no;
    public String tea_name;
    public String tea_email;
    public String tea_phone;
    public String tea_nickname;
    public String tea_intro;
    public String tea_detail;
    public String tea_career;
    public String tea_nation;

}
