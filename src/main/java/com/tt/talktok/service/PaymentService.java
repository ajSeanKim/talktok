package com.tt.talktok.service;

import com.tt.talktok.dto.PaymentDto;
import com.tt.talktok.entity.Payment;
import com.tt.talktok.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private PaymentDto convertToDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setLec_name(payment.getLec_name());
        paymentDto.setStu_Email(payment.getStuEmail());
        paymentDto.setPay_time(payment.getPay_time());
        paymentDto.setLec_no(payment.getLecNo());
        paymentDto.setPay_no(payment.getPayNo());
        paymentDto.setPay_price(payment.getPay_price());
        return paymentDto;
    }
    // 결제 정보 저장
    public int save(PaymentDto paymentDto) {
        return paymentRepository.save(paymentDto);
    }

    public List<PaymentDto> findPaymentByStudentEmail(String stuEmail) {
        List<Payment> payment = paymentRepository.findByStuEmail(stuEmail);
        return payment.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
