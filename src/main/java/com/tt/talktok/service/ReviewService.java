package com.tt.talktok.service;

import com.tt.talktok.dto.ReviewDto;
import com.tt.talktok.entity.Review;
import com.tt.talktok.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private static final Logger log = LoggerFactory.getLogger(ReviewService.class);
    private final ReviewRepository reviewRepository;

    public void reviewWrite(ReviewDto reviewDto) {

        LocalDateTime currentDateTime = LocalDateTime.now();

        Review review = Review
                .builder()
                .revName(reviewDto.getRev_name())
                .revDetail(reviewDto.getRev_detail())
                .revWriter(reviewDto.getRev_writer())
                .revScore(reviewDto.getRev_score())
                .revDate(currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .lecNo(reviewDto.getLec_no())
                .teaNo(reviewDto.getTea_no())
                .build();
        reviewRepository.save(review);
    }

    public Page<ReviewDto> reviewFindAll(Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAll(pageable);
        return reviews.map(this::convertToDto);
    }

    private ReviewDto convertToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setRev_no(review.getRevNo());
        reviewDto.setRev_name(review.getRevName());
        reviewDto.setRev_detail(review.getRevDetail());
        reviewDto.setRev_writer(review.getRevWriter());
        reviewDto.setRev_score(review.getRevScore());
        reviewDto.setRev_date(review.getRevDate());
        reviewDto.setLec_no(review.getLecNo());
        reviewDto.setTea_no(review.getTeaNo());
        reviewDto.setRev_detail(reviewDto.getRev_detail().replace("\\n","<br/>"));
        // 엔티티 클래스의 필드를 DTO 클래스에 설정

        return reviewDto;
    }
}
