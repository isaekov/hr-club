package ru.hwru.integration.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) {
            return "redirect:/"; // if user already logged in redirect back to root context
        } else {
            return "auth/login";
        }

    }
}
