package com.tt.talktok.repository;

import com.tt.talktok.dto.PaymentDto;
import com.tt.talktok.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    // 결제 정보 저장
    //public int savePayment(PaymentDto paymentDto);
    public int save(PaymentDto paymentDto);
}
