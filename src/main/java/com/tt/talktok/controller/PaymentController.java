package com.tt.talktok.controller;

import com.tt.talktok.dto.PaymentDto;
import com.tt.talktok.entity.Payment;
import com.tt.talktok.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/payment")
    public String payment(Model model, HttpSession session) {
        String email = (String) session.getAttribute("stuEmail");
        System.out.println(email);
        model.addAttribute("email", email);
        return "/lecture/payments";
    }

    @PostMapping("/savePayment")
    @ResponseBody
    public ResponseEntity<String> savePayment(@RequestBody Map<String, Object> reqeustData) {

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPay_time(LocalDate.now());
        paymentDto.setPay_price((String) reqeustData.get("price"));
        paymentDto.setLec_no((int) reqeustData.get("lecNo"));
        paymentDto.setLec_name((String) reqeustData.get("lecName"));
        paymentDto.setStu_Email((String) reqeustData.get("email"));

        int result = paymentService.save(paymentDto);
        if(result == 1) {
            return ResponseEntity.ok("Payment 저장 완료");
        } else {
            return (ResponseEntity<String>) ResponseEntity.badRequest();
        }
    }
}
