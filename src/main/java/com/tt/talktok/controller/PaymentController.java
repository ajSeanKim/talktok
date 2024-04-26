package com.tt.talktok.controller;

import com.tt.talktok.dto.PaymentDto;
import com.tt.talktok.entity.Payment;
import com.tt.talktok.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/savePayment")
    @ResponseBody
    public ResponseEntity<String> savePayment(@RequestBody PaymentDto paymentDto) {

        return ResponseEntity.ok("Payment 저장 완료");
    }
}
