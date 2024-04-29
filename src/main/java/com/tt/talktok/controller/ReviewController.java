package com.tt.talktok.controller;

import com.tt.talktok.dto.ReviewDto;
import com.tt.talktok.dto.StudentDto;
import com.tt.talktok.entity.Review;
import com.tt.talktok.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("review")
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/list")
    public String reviewAllFind(Model model, @PageableDefault(size = 10, sort = "revNo", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ReviewDto> reviews = reviewService.reviewFindAll(pageable);
        log.info("review: {}", reviews.getContent());
        model.addAttribute("reviews", reviews.getContent());
        model.addAttribute("page", reviews);
        return "review/list";
    }

    @GetMapping("/detail")
    public String reviewDetail(Model model, int rev_no) {
        ReviewDto review = reviewService.reviewFindDetail(rev_no);
        log.info("review: {}", review);
        model.addAttribute("review", review);
        return "/review/detail";
    }

    @GetMapping("/write")
    public String writeForm(ReviewDto reviewDto){
        return "review/writeForm";
    }
    @PostMapping("/write")
    public String write(ReviewDto reviewDto){
        log.info("reviewDto: {}", reviewDto);
        reviewService.reviewWrite(reviewDto);
        return "redirect:/review/list";
    }

    @GetMapping("/mylist")
    public String myReview(HttpServletRequest request, Model model, @PageableDefault(size = 10, sort = "revNo", direction = Sort.Direction.DESC) Pageable pageable) {
        HttpSession session = request.getSession();
        int stuNo = (int) session.getAttribute("stuNo"); // 세션에서 사용자 번호 가져오기
        System.out.println(stuNo);
        List<ReviewDto> stuReview = reviewService.findReviewsByStudentNo(stuNo);
        Page<ReviewDto> reviews = reviewService.reviewFindAll(pageable);
        log.info("review: {}", reviews.getContent());

        model.addAttribute("reviews", reviews.getContent());
        model.addAttribute("page", reviews);
        model.addAttribute("stuReviews", stuReview);
        model.addAttribute("stuNo", stuNo); // 모델에 사용자 번호 추가

        System.out.println(stuNo);
        System.out.println(session.getAttribute("stuNo"));
        return "review/myReview";
    }


}
