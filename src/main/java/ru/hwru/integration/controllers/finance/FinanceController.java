package ru.hwru.integration.controllers.finance;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hwru.integration.entity.Product;
import ru.hwru.integration.entity.ProductCategory;
import ru.hwru.integration.repository.finance.ProductCategoryRepository;
import ru.hwru.integration.repository.finance.ProductRepository;
import ru.hwru.integration.service.finance.FinanceService;

@Controller
@RequestMapping("/finance")
public class FinanceController {

    private final FinanceService financeService;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    public FinanceController(FinanceService financeService, ProductCategoryRepository repository, ProductRepository productRepository) {
        this.financeService = financeService;
        this.productCategoryRepository = repository;
        this.productRepository = productRepository;
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
    public String createList() {
        return "finance/create-list";
    }
}
