package com.nonoplan.api.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginDTO {
    private String token; // 토큰
    private Long userId; // 사용자 id
}
