package com.tt.talktok.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
public class Payment {
    @Id
    @Column(name = "pay_no")
    private int payNo;
    private Date pay_time;
    private String pay_price;
    private String pay_status;
    @Column(name = "stu_no")
    private int stuNo;
    @Column(name = "lec_no")
    private int lecNo;
}
