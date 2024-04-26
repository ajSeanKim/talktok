package com.tt.talktok.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
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
    private Timestamp rev_date;
    private int lec_no;
    private String lec_name;
    private int tea_no;
    private String tea_name;

}
