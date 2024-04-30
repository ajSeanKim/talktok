package com.tt.talktok.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Payment {
    @Id
    @Column(name = "pay_no")
    private int payNo;
    private Timestamp pay_time;
    private String pay_price;
    @Column(name = "lec_no")
    private int lecNo;
    private String lec_name;
    @Column(name = "stu_email")
    private String stuEmail;
}
