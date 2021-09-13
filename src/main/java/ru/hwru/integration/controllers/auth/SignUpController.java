package ru.hwru.integration.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import ru.hwru.integration.entity.User;
import ru.hwru.integration.service.auth.UserService;
import org.springframework.security.core.Authentication;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;

@Controller
public class SignUpController {

    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegisterForm(Model model) {
        // create new BlogUser instance and pass it to registerForm view template
        User blogUser = new User();
        model.addAttribute("user", blogUser);
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@Valid @ModelAttribute User blogUser,
                                  BindingResult bindingResult,
                                  SessionStatus sessionStatus) throws RoleNotFoundException {


        System.out.println(blogUser);
        userService.saveNewUser(blogUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                blogUser.getUsername(), blogUser.getPassword(), blogUser.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        sessionStatus.setComplete();
        return "redirect:/";



    }

    }
