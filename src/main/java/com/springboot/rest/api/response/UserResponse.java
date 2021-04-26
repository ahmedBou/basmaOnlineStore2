package com.springboot.rest.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class UserResponse {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String email;
}
