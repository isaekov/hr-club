package ru.hwru.integration.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.hwru.integration.entity.Authority;
import ru.hwru.integration.repository.AuthorityRepository;
import ru.hwru.integration.service.auth.UserService;


@ControllerAdvice
public class AdController {

    private final UserService userService;

    private final AuthorityRepository authorityRepository;

    public AdController(UserService userService, AuthorityRepository authorityRepository) {
        this.userService = userService;
        this.authorityRepository = authorityRepository;
    }


    @ModelAttribute("settings")
    public String set() {

//        Authority authority = authorityRepository.findByAuthority("ROLE_USER");
//
//        if (authority == null) {
//            Authority authority1 = new Authority();
//            authority1.setAuthority("ROLE_USER");
//            authorityRepository.saveAndFlush(authority1);
//        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

}
