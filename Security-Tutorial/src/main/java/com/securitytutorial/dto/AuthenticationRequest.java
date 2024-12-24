package com.securitytutorial.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest
{
    private String employeeEmail;
    private String employeePassword;
}
