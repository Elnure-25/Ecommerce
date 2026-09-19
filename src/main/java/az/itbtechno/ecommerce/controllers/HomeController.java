package az.itbtechno.ecommerce.controllers;

import az.itbtechno.ecommerce.dto.category.CategoryHomeDTO;
import az.itbtechno.ecommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService CategoryService;

    @GetMapping("/")
    public String index(Model model) {

        List<CategoryHomeDTO> CategoryList = CategoryService.categoryList();

        model.addAttribute("categories", CategoryList);

        return "index.html";
    }
    @GetMapping("/contact")
    public String contact(){
        return "contact.html";
    }
}