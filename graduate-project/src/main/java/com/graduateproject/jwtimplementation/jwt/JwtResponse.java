package com.graduateproject.jwtimplementation.jwt;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String token;
    private String authority;
}
