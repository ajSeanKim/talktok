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


    // JpaRepository에서 제공하는 save() 메서드를 사용하여 저장
//    @Override
//    <S extends Payment> S save(S entity);
}
