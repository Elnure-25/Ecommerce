package az.itbtechno.ecommerce.controllers;

import az.itbtechno.ecommerce.dto.category.CategoryHomeDTO;
import az.itbtechno.ecommerce.dto.product.ProductDashboardDTO;
import az.itbtechno.ecommerce.dto.product.ProductHotTrendDTO;
import az.itbtechno.ecommerce.services.CategoryService;
import az.itbtechno.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/")
    public String index(Model model) {

        List<CategoryHomeDTO> categoryList =
                categoryService.categoryList();

        List<ProductDashboardDTO> products =
                productService.getProducts();

        List<ProductHotTrendDTO> hotTrendDTOList =
                productService.getHotTrends();

        List<ProductHotTrendDTO> bestSellerDTOList =
                productService.getBestSellers();

        List<ProductHotTrendDTO> featureDTOList =
                productService.getFeatures();

        model.addAttribute("categories", categoryList);
        model.addAttribute("products", products);
        model.addAttribute("hotTrendProducts", hotTrendDTOList);
        model.addAttribute("bestSellerProducts", bestSellerDTOList);
        model.addAttribute("featureProducts", featureDTOList);

        return "index.html";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact.html";
    }

}

