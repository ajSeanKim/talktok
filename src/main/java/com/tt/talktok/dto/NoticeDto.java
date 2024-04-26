package com.tt.talktok.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeDto {

    private Integer noNo;
    private String no_subject;
    private String no_content;
    private Integer no_readcount;
    private Date no_date;

}
