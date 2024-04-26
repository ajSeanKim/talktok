package com.tt.talktok.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PaymentDto {
    private int pay_no;
    private Date pay_time;
    private String pay_price;
    private String pay_status;
    private int stu_no;
    private int lec_no;
}
