package com.tt.talktok.controller;


import com.tt.talktok.dto.LectureDto;
import com.tt.talktok.dto.StudentDto;
import com.tt.talktok.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController<studentDto> {

    private final CartService cartService;

    // 장바구니 목록 조회
    @GetMapping("/list")
    public String cartList(Model model) {
        return "student/cart";
    }

    // 장바구니 추가
    @PostMapping("/add")
    public String cart(@RequestParam("no") int lec_no, HttpSession session) {
        StudentDto studentDto = (StudentDto) session.getAttribute("studentDto");

        if(studentDto == null){
            return "redirect:/student/login";
        }

        cartService.addCart(studentDto.getStuNo(), lec_no);
        System.out.println("장바구니 추가");
        return "redirect:/student/cart";
    }
}
