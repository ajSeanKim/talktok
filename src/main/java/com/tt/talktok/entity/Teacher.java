package com.tt.talktok.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int tea_no;

    private String tea_name;
    private String tea_email;
    private String tea_pwd;
    private String tea_phone;
    private String tea_nickname;
    private String tea_account;
    private String tea_intro;
    private String tea_detail;
    private String tea_career;
    private String tea_nation;

}