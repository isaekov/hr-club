package ru.hwru.integration.controllers.admin.ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/product")
public class ProductController {



    @GetMapping
    public String list() {

        return "admin/product/list";
    }

    @GetMapping("/detail")
    @ResponseBody
    public String detail(@RequestParam(required = false) String productId) {
        return "return " + productId;
    }

    @GetMapping("/create")
    public String create() {
        return template("create");
    }

    private String template(String template) {
        return "admin/product/".concat(template);
    }
}
