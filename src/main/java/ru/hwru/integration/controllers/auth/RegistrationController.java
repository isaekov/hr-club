package ru.hwru.integration.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.hwru.integration.entity.User;
import ru.hwru.integration.service.auth.UserService;
import org.springframework.security.core.Authentication;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegisterForm(@RequestParam(required = false) String error, Model model) {
        return "auth/registration";
    }

    @PostMapping(path = "/registration")
    public String registerNewUser(User user, Model model) throws UnsupportedEncodingException {

        model.addAttribute("name", user.name);
        model.addAttribute("username", user.getUsername());
        try {
            userService.saveNewUser(user);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "auth/registration";
        }
        return "redirect:/login";
    }
}
