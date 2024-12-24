package com.securitytutorial.dto;

import com.securitytutorial.model.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest
{
    private String employeeName;
    private String employeeMail;
    private String employeePassword;
    private String employeePhone;

    @Enumerated(EnumType.STRING)
    private Role role;
}
