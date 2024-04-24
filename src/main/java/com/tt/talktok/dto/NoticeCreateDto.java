package com.tt.talktok.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NoticeCreateDto {

    private int no_no;
    private String no_subject;
    private String no_content;
    private int no_readcount;
    private Date no_date;

    @Builder
    public NoticeCreateDto(String no_subject, String no_content, int no_readcount) {
        this.no_subject = no_subject;
        this.no_content = no_content;
        this.no_readcount = no_readcount;
    }

}
