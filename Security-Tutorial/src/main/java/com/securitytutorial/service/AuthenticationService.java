package com.securitytutorial.service;

import com.securitytutorial.dto.AuthenticationRequest;
import com.securitytutorial.dto.AuthenticationResponse;
import com.securitytutorial.dto.RegisterRequest;
import com.securitytutorial.model.EmployeeModel;
import com.securitytutorial.model.Role;
import com.securitytutorial.repository.EmployeeRepo;
import com.securitytutorial.security.JwtAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService
{
    @Autowired
    private EmployeeRepo empRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtAuthService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var employee = EmployeeModel.builder()
                .employeeName(registerRequest.getEmployeeName())
                .employeeMail(registerRequest.getEmployeeMail())
                .employeePhone(registerRequest.getEmployeePhone())
                .employeePassword(passwordEncoder.encode(registerRequest.getEmployeePassword()))
                .role(Role.EMPLOYEE)
                .build();
        empRepo.save(employee);
        var jwtToken = jwtService.generateToken(employee);
        var refreshToken = jwtService.generateRefresh(new HashMap<>(), employee);
        return AuthenticationResponse.builder()
                .authenticationToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse registerAdmin(RegisterRequest registerRequest) {
        var employee = EmployeeModel.builder()
                .employeeName(registerRequest.getEmployeeName())
                .employeeMail(registerRequest.getEmployeeMail())
                .employeePhone(registerRequest.getEmployeePhone())
                .employeePassword(passwordEncoder.encode(registerRequest.getEmployeePassword()))
                .role(Role.ADMIN)
                .build();
        empRepo.save(employee);
        var jwtToken = jwtService.generateToken(employee);
        var refreshToken = jwtService.generateRefresh(new HashMap<>(), employee);
        return AuthenticationResponse.builder()
                .authenticationToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmployeeEmail(), authenticationRequest.getEmployeePassword())
        );
        var user = empRepo.findByEmployeeMail(authenticationRequest.getEmployeeEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
        return AuthenticationResponse.builder()
                .authenticationToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse refreshToken(String refreshToken) {

        var user = empRepo.findByEmployeeMail(jwtService.getEmailFromToken(refreshToken)).orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));
        var jwtToken = jwtService.generateToken(user);
        var newRefreshToken = jwtService.generateRefresh(new HashMap<>(), user);
        return AuthenticationResponse.builder()
                .authenticationToken(jwtToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    public Boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }
}
