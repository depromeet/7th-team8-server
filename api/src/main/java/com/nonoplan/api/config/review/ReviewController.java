package com.nonoplan.api.config.review;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

    @ApiOperation("사용자가 작성한 리뷰를 가져옵니다.")
    @GetMapping("")
    public ResponseEntity<Page<ReviewDTO>> getUserReviews(@RequestParam(required = true) Long userId,
                                                          @RequestParam(required = false) String keyword,
                                                          @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                          @RequestParam(required = false, defaultValue = "30") int pageSize) {

        var reviewDTO1 = ReviewDTO.builder()
                .reviewId(4L)
                .userId(1L)
                .locationId(1L)
                .username("nonoPlanner")
                .content("손님이 너무 많아서 기다리다 1시간 들어갔는데 맛있었어요 담에 또 올 듯")
                .rating(4.0)
                .lastUpdateAt(LocalDateTime.now().minusDays(15L))
                .build();


        var reviewDTO2 = ReviewDTO.builder()
                .reviewId(5L)
                .userId(1L)
                .locationId(4L)
                .username("nonoPlanner")
                .content("버스 타고 오긴 불편하지만 좋아요 경치 넘 예뻐여 인생샷 건지기 좋아욤")
                .rating(5.0)
                .lastUpdateAt(LocalDateTime.now().minusDays(10L))
                .build();

        var reviewDTO3 = ReviewDTO.builder()
                .reviewId(5L)
                .userId(1L)
                .username("nonoPlanner")
                .locationId(2L)
                .content("제주도 오면 꼭 들러야하는 곳")
                .rating(5.0)
                .lastUpdateAt(LocalDateTime.now().minusDays(1L))
                .build();

        var reviews = List.of(reviewDTO1, reviewDTO2, reviewDTO3);
        var page = new PageImpl<>(reviews);
        return ResponseEntity.ok().body(page);
    }


    @ApiOperation("북마크한 리뷰를 가져옵니다.")
    @GetMapping("/bookmark")
    public ResponseEntity<Page<ReviewDTO>> getBookmarkedReviews(@RequestParam(required = true) Long userId,
                                                                @RequestParam(required = false) String keyword,
                                                                @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                                @RequestParam(required = false, defaultValue = "30") int pageSize) {
        var reviewDTO1 = ReviewDTO.builder()
                .reviewId(6L)
                .userId(1L)
                .locationId(1L)
                .username("사용자이름")
                .content("동백꽃 군락지 여기가 진짜에여! 존예 겨울에 가야 예뻐요")
                .rating(4.5)
                .lastUpdateAt(LocalDateTime.now().minusDays(1L))
                .build();

        var reviewDTO2 = ReviewDTO.builder()
                .reviewId(2L)
                .userId(1L)
                .locationId(2L)
                .username("사용자 이름1")
                .content("리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용")
                .rating(5.0)
                .lastUpdateAt(LocalDateTime.now().minusDays(10L))
                .build();

        var reviews = List.of(reviewDTO1, reviewDTO2);
        var page = new PageImpl<>(reviews);
        return ResponseEntity.ok().body(page);
    }

    @ApiOperation("특정 리뷰 수정(리뷰 id 필요)")
    @PutMapping("/{id}")
    public ResponseEntity<Void> putReview(@PathVariable Long id,
                                          @RequestParam(required = true) int userId,
                                          @RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok().build();
    }

    @ApiOperation("특정 리뷰 삭제 (리뷰 id 필요)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id,
                                             @RequestParam(required = true) int userId) {
        return ResponseEntity.ok().build();
    }

}
