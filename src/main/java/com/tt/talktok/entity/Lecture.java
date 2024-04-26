package com.tt.talktok.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lecture")
public class Lecture {
    @Id
    @Column(name = "lec_no")
    private int lecNo;
    private String lec_name;
    private String lec_day;
    private String lec_time;
    private String lec_price;
    private String lec_detail;
    private String lec_startdate;
    private String lec_enddate;
}
