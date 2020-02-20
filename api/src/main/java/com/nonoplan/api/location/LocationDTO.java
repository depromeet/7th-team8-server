package com.nonoplan.api.location;

import com.nonoplan.core.location.Category;
import com.nonoplan.core.location.SubCategory;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class LocationDTO {
    private long locationId;
    private String name;
    private String description; // 설명
    private Category category;
    private SubCategory subCategory; // 서브 카테고리
    private String address; // 주소
    private double latitude; // 위도
    private double longitude; // 경도
    private String phoneNumber; // 전화 번호
    private String businessHours; // 영업 시간
    private String detail; // 상세 정보
    private List<String> images; // 이미지 정보
    private double rating;
    private int reviewCount;
    private boolean isBookmarking; // 북마크 여부
}
