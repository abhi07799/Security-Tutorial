package com.securitytutorial.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController
{
    @GetMapping("demo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String dummy()
    {
        return "Jai Bajrang Bali";
    }
}
