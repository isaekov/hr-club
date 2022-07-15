package ru.hwru.integration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping("email")
public class EmailController {

    @GetMapping("settings")
    public String settings() {
        return "email/settings";
    }
}
