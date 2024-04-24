package com.tt.talktok.dto;

import com.tt.talktok.entity.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
public class NoticeResponseDto {

    private final int no_no;
    private final String no_subject;
    private final String no_content;
    private final int no_readcount;
    private final Date no_date;

    public NoticeResponseDto(Notice notice){
        this.no_no = notice.getNo_no();
        this.no_subject = notice.getNo_subject();
        this.no_content = notice.getNo_content();
        this.no_readcount = notice.getNo_readcount();
        this.no_date = notice.getNo_date();
    }

    @Builder
    public NoticeResponseDto(int noNo, int no_no, String no_subject, String no_content, int no_readcount, Date no_date, Date noDate) {
        this.no_no = no_no;
        this.no_subject = no_subject;
        this.no_content = no_content;
        this.no_readcount = no_readcount;
        this.no_date = no_date;
    }

}
