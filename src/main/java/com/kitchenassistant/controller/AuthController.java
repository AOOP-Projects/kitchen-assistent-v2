package com.kitchenassistant.controller;
 
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.ModelAttribute;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 
 import com.kitchenassistant.model.User;
 import com.kitchenassistant.service.UserService;
 
 @Controller
 public class AuthController {
 
     private final UserService userService;
 
     public AuthController(UserService userService) {
         this.userService = userService;
     }
 
     // ✅ Login page (GET)
     @GetMapping("/login")
     public String loginPage(@RequestParam(value = "registered", required = false) String registered,
                             @RequestParam(value = "error", required = false) String error,
                             @RequestParam(value = "logout", required = false) String logout,
                             Model model) {
         if (registered != null) {
             model.addAttribute("success", "Registration successful! You can now log in.");
         }
         if (error != null) {
             model.addAttribute("error", "Invalid username or password.");
         }
         if (logout != null) {
             model.addAttribute("success", "You have been logged out.");
         }
         return "login";
     }
 
     // ✅ Registration form (GET)
     @GetMapping("/register")
     public String registerPage(Model model) {
         model.addAttribute("user", new User());
         return "register";
     }
 
     // ✅ Registration form (POST)
     @PostMapping("/register")
     public String registerUser(@ModelAttribute("user") User user, Model model) {
         if (userService.existsByUsername(user.getUsername())) {
             model.addAttribute("error", "Username already exists. Please choose another one.");
             return "register";
         }
 
         userService.save(user);  // 🔒 Password is encoded in service
         return "redirect:/login?registered";
     }
 }