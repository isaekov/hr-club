package ru.hwru.integration.controllers.finance;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/finance")
public class FinanceController {

    @GetMapping("/list")
    public String list() {
        return "finance/list";
    }
}
