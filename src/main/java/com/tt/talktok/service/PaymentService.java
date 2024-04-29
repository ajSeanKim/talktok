package com.tt.talktok.service;

import com.tt.talktok.dto.PaymentDto;
import com.tt.talktok.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    // 결제 정보 저장
    public int save(PaymentDto paymentDto) {
        return paymentRepository.save(paymentDto);
    }
}
