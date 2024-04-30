package com.tt.talktok.controller;


import com.tt.talktok.dto.LectureDto;
import com.tt.talktok.dto.StudentDto;
import com.tt.talktok.entity.Cart;
import com.tt.talktok.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController<studentDto> {

    private final CartService cartService;

    // 장바구니 목록 조회
    @GetMapping("/list")
    public String cartList(HttpSession session, Model model) {
        StudentDto studentDto = (StudentDto) session.getAttribute("studentDto");

        if(studentDto == null){
            return "redirect:/student/login";
        }else{
            List<Cart> cartItems = cartService.getCartItems(studentDto.getStuNo());
            model.addAttribute("cartItems", cartItems);
        }

        return "student/cart";
    }

    // 장바구니 추가
    @PostMapping("/add")
    public String cart(@RequestParam("lec_no") int lec_no, HttpSession session) {
        StudentDto studentDto = (StudentDto) session.getAttribute("studentDto");

        System.out.println("studentDto = " + studentDto);

        if(studentDto == null){
            return "redirect:/student/login";

        }

        System.out.println("studentDto.getStuNo() : "+studentDto.getStuNo());
        System.out.println("lectureDto.getLec_no() : "+lec_no);

        cartService.addCart(studentDto.getStuNo(), lec_no);
        System.out.println("장바구니 추가");
        return "redirect:/student/cart";
    }
}
