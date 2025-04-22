package com.kitchenassistant.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String role = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .findFirst()
                .orElse("ROLE_USER");

        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("role", role);
        return "dashboard";
    }
}
