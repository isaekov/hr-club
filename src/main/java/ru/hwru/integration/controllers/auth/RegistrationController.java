package ru.hwru.integration.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hwru.integration.dto.UserRegistration;
import ru.hwru.integration.service.auth.UserService;

@Controller
@RequestMapping("/auth")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistration userRegistrationDTO(){
        return new UserRegistration();
    }

    @GetMapping("/registration")
    public String showRegistrationForm(){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") UserRegistration userRegistration){
        userService.save(userRegistration);
        return "redirect:/";

    }
}
