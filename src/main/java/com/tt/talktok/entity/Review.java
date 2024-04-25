package com.tt.talktok.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rev_no")
    private Long revNo;

    @Column(name = "rev_name")
    private String revName;
    @Column(name = "rev_detail")
    private String revDetail;
    @Column(name = "rev_writer")
    private String revWriter;
    @Column(name = "rev_readcount")
    private String revReadCount;
    @Column(name = "rev_score")
    private int revScore;
    @Column(name = "rev_date")
    private String revDate;
    @Column(name = "lec_no")
    private int lecNo;
    @Column(name = "tea_no")
    private int teaNo;

}
