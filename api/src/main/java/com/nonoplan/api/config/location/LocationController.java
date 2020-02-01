package com.nonoplan.api.config.location;

import com.nonoplan.api.config.review.ReviewDTO;
import com.nonoplan.core.location.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/locations", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {

    @GetMapping("")
    public ResponseEntity<Page<LocationDTO>> getLocations(@RequestParam double lat,
                                                          @RequestParam double lng,
                                                          @RequestParam(required = false) String keyword,
                                                          @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                          @RequestParam(required = false, defaultValue = "30") int pageSize) {
        var locationDTO1 = LocationDTO.builder()
                .locationId(1L)
                .name("동백키친")
                .address("제주특자치도 제주시 한림 수원7 42")
                .businessHours("월,화,수,금,토,일 11:00 ~ 20:00\n월,화,수,금 브레이크타임 15:00 ~ 16:00")
                .category(Category.RESTAURANT)
                .phoneNumber("064-796-1015")
                .images(List.of("http://img1.daumcdn.net/thumb/T680x420/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flocalfiy%2Fsearchregister_1505814184", "http://img1.daumcdn.net/thumb/T680x420/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flocalfiy%2Fsearchregister_1505814184"))
                .rating(4.3)
                .latitude(100.0)
                .longitude(200.0)
                .isBookmarking(false)
                .build();

        var locationDTO2 = LocationDTO.builder()
                .locationId(2L)
                .name("청춘부부")
                .address("제주특별자치도 서귀포시 대정읍 추사로38번길 181")
                .businessHours("월,목,금,토,일 10:00 ~ 19:00\n휴무일 화,수요일")
                .category(Category.CAFE)
                .phoneNumber("010-6657-1529")
                .images(List.of("https://search.pstatic.net/common/?autoRotate=true&quality=95&src=http%3A%2F%2Fldb.phinf.naver.net%2F20181007_103%2F1538919508879rvKh5_JPEG%2FJ-cKxE7CucXGUSS_9Ilu-SHm.jpeg.jpg&type=m1000_692", "https://search.pstatic.net/common/?autoRotate=true&quality=95&src=http%3A%2F%2Fldb.phinf.naver.net%2F20181007_103%2F1538919508879rvKh5_JPEG%2FJ-cKxE7CucXGUSS_9Ilu-SHm.jpeg.jpg&type=m1000_692"))
                .rating(3.0)
                .latitude(10.0)
                .longitude(20.0)
                .isBookmarking(true)
                .build();

        var locationDTO3 = LocationDTO.builder()
                .locationId(3L)
                .name("올레길 7코스(서귀포-월평 올레)")
                .address("제주특별자치도 서귀포시 중정로 22")
                .description("올레인들이 가장 사랑하고 아끼는 자연생태길인‘수봉로’를 만날 수 있는 올레길 7코스")
                .category(Category.ATTRACTIONS)
                .images(List.of("img1.daumcdn.net/thumb/T680x420/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fplace%2F6B250958205C498F8CEFBACD1B991C1A", "img1.daumcdn.net/thumb/T680x420/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fplace%2F6B250958205C498F8CEFBACD1B991C1A"))
                .rating(4.0)
                .latitude(130.0)
                .longitude(210.0)
                .isBookmarking(true)
                .build();

        var locations = List.of(locationDTO1, locationDTO2, locationDTO3);
        var page = new PageImpl<>(locations);
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable Long id) {
        var locationDTO = LocationDTO.builder()
                .locationId(id)
                .name("동백키친")
                .address("제주특자치도 제주시 한림 수원7 42")
                .businessHours("월,화,수,금,토,일 11:00 ~ 20:00\n월,화,수,금 브레이크타임 15:00 ~ 16:00")
                .category(Category.RESTAURANT)
                .phoneNumber("064-796-1015")
                .images(List.of("http://img1.daumcdn.net/thumb/T680x420/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flocalfiy%2Fsearchregister_1505814184", "http://img1.daumcdn.net/thumb/T680x420/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flocalfiy%2Fsearchregister_1505814184"))
                .rating(4.3)
                .latitude(100.0)
                .longitude(200.0)
                .isBookmarking(false)
                .build();

        return ResponseEntity.ok(locationDTO);
    }

    @GetMapping("{id}/reviews")
    public ResponseEntity<Page<ReviewDTO>> getLocationReviews(@PathVariable Long id,
                                                              @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                              @RequestParam(required = false, defaultValue = "30") int pageSize) {
        var reviewDTO1 = ReviewDTO.builder()
                .reviewId(1L)
                .userId(1L)
                .username("내가 쓴 리뷰 1")
                .content("리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1리뷰 내용 1")
                .rating(4.5)
                .lastUpdateAt(LocalDateTime.now().minusDays(1L))
                .build();

        var reviewDTO2 = ReviewDTO.builder()
                .reviewId(2L)
                .userId(2L)
                .username("사용자 이름 1")
                .content("리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2리뷰 내용 2")
                .rating(5.0)
                .lastUpdateAt(LocalDateTime.now().minusDays(10L))
                .build();

        var reviewDTO3 = ReviewDTO.builder()
                .reviewId(3L)
                .userId(3L)
                .username("사용자 이름 2")
                .content("리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3리뷰 내용 3")
                .rating(3.1)
                .lastUpdateAt(LocalDateTime.now().minusDays(30L))
                .build();

        var reviews = List.of(reviewDTO1, reviewDTO2, reviewDTO3);
        var page = new PageImpl<>(reviews);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping("{id}/reviews")
    public ResponseEntity<Void> postLocationReview(ReviewDTO reviewDTO) {

        return ResponseEntity.ok().build();
    }
}
