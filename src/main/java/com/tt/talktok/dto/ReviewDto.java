package com.tt.talktok.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class ReviewDto {

    private Long rev_no;
    private String rev_name;
    private String rev_detail;
    private String rev_writer;
    private int rev_score;
    private String rev_date;
    private int lec_no;
    private int tea_no;
}
