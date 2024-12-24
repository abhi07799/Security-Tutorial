package com.securitytutorial.dto;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse
{
    private String authenticationToken;
    private String refreshToken;
}
