package com.nonoplan.api.config.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDTO {
    private long userId;
    private String username;
    private String userEmail;
    private String userImage;
}
