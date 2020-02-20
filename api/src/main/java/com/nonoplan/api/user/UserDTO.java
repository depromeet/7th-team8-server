package com.nonoplan.api.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDTO {
    private long userId;
    private String username;
    private String userEmail;
}
