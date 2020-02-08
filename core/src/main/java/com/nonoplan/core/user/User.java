package com.nonoplan.core.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column("user_id")
    private Long id;
    @Enumerated
    private String auth;

    private String name;
    private String email;
    private LocalDateTime createdAt;
}
