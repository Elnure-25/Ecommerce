package az.itbtechno.ecommerce.controllers;

import az.itbtechno.ecommerce.dto.response.category.CategoryHomeDTO;
import az.itbtechno.ecommerce.models.Category;
import az.itbtechno.ecommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model) {

        List<CategoryHomeDTO> categoryList = categoryService.categoryList();

        model.addAttribute("categories", categoryList);

        return "index.html";
    }
    @GetMapping("/contact")
    public String contact(){
        return "contact.html";
    }
}