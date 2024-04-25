package com.tt.talktok.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no_no;
    private String no_subject;
    private String no_content;
    private int no_readcount;
    @CreationTimestamp
    private Timestamp no_date;

    @Builder
    public Notice(String no_subject, String no_content, int no_readcount) {
        this.no_subject = no_subject;
        this.no_content = no_content;
        this.no_readcount = no_readcount;
    }

}
