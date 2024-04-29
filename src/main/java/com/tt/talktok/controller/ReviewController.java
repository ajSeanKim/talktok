package com.tt.talktok.controller;

import com.tt.talktok.dto.ReviewDto;
import com.tt.talktok.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public String reviewAllFind(Model model, @PageableDefault(page = 0, size = 10, sort = "revNo", direction = Sort.Direction.DESC) Pageable pageable
                                            ,@RequestParam(required = false, name = "search_target") String search_target,@RequestParam(required = false, name = "keyword") String keyword) {

        Page<ReviewDto>reviews = reviewService.reviewFindAll(search_target, keyword, pageable);


        log.info("review: {}", reviews.getContent());

        model.addAttribute("keyword", keyword);
        model.addAttribute("search_target", search_target);
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

    @GetMapping("/able")
    public String able(Model model, @PageableDefault(size = 10, sort = "revDate", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam int stu_no) {

        Page<ReviewDto> reviews = reviewService.reviewFindAble(stu_no, pageable);
        model.addAttribute("reviews", reviews.getContent());
        return "review/able";
    }

    @GetMapping("/update")
    public String updateForm(){
        return "review/updateForm";
    }
    @GetMapping("/delete")
    public String deleteForm(){
        return "review/deleteForm";
    }

}
