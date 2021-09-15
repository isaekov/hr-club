package ru.hwru.integration.controllers.finance;

import ru.hwru.integration.util.dto.FormProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hwru.integration.entity.MonthListProduct;
import ru.hwru.integration.entity.Product;
import ru.hwru.integration.entity.ProductCategory;
import ru.hwru.integration.repository.finance.MonthListProductRepository;
import ru.hwru.integration.repository.finance.ProductCategoryRepository;
import ru.hwru.integration.repository.finance.ProductRepository;
import ru.hwru.integration.service.finance.FinanceService;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/finance")
public class FinanceController {

    private final FinanceService financeService;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final MonthListProductRepository monthListProductRepository;

    public FinanceController(FinanceService financeService, ProductCategoryRepository repository, ProductRepository productRepository, MonthListProductRepository monthListProductRepository) {
        this.financeService = financeService;
        this.productCategoryRepository = repository;
        this.productRepository = productRepository;
        this.monthListProductRepository = monthListProductRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("productCategory", new ProductCategory());
        model.addAttribute("product", new Product());
        model.addAttribute("categoryList", productCategoryRepository.findAll());
        model.addAttribute("productList", productRepository.findAll());
        return "finance/index";
    }

    @PostMapping("/category/add")
    public String add(@ModelAttribute ProductCategory category) {
        financeService.add(category);
        return "redirect:/finance";
    }

    @PostMapping("/product/add")
    public String add(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/finance";
    }

    @GetMapping("/category/delete/{id}")
    public String categoryDelete(@PathVariable("id") Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        productCategoryRepository.delete(category);
        return "redirect:/finance";
    }

    @GetMapping("/product/delete/{id}")
    public String productDelete(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        productRepository.delete(product);
        return "redirect:/finance";
    }

    @GetMapping("/create-list")
    public String createList(Model model) {

        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("monthListProduct", monthListProductRepository.findAll());
        return "finance/create-list";
    }

    @PostMapping("/create-list/add")
    public String createListAdd(@RequestParam("product") List<MonthListProduct> monthListProducts) {

//
        monthListProducts.forEach(System.out::println);

//        System.out.println(monthListProducts.getProduct());
//        for (MonthListProduct mont :
//
//                monthListProducts) {
//            System.out.println(mont.getProduct());
//        }
        return "redirect:/finance/create-list";
    }
}
