package com.tt.talktok.service;

import com.tt.talktok.entity.Cart;
import com.tt.talktok.entity.Lecture;
import com.tt.talktok.entity.Student;
import com.tt.talktok.repository.CartRepository;
import com.tt.talktok.repository.LectureRepository;
import com.tt.talktok.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;

    public void addCart(int stuNo, int LecNo) {

        Student student = studentRepository.findById(stuNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다: " + stuNo));
        Lecture lecture = lectureRepository.findById(LecNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다: " + LecNo));

        Cart cart = Cart.builder()
                .student(student)
                .lecture(lecture)
                .caCount(1) // 장바구니에 추가될 갯수, 기본값 1
                .lecPrice(lecture.getLec_price())
                .build();

        cartRepository.save(cart);
    }


    public List<Cart> getCartItems(int stuNo) {
        return cartRepository.findByStudentStuNo(stuNo);
    }


}
