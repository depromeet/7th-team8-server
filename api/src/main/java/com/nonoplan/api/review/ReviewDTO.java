package com.nonoplan.api.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ReviewDTO {
    private long reviewId; // 리뷰 id
    private long userId; // 작성자 id
    private long locationId; // 장소 id
    private String username; // 사용자 이름
    private String content; // 작성 내용
    private double rating; // 평점
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime lastUpdateAt; // 수정 시각
}
