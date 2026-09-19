package az.itbtechno.ecommerce.controllers;

import az.itbtechno.ecommerce.dto.product.ProductDashboardDTO;
import az.itbtechno.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;

    @GetMapping("/shop")
    public String index(Model model) {

        List<ProductDashboardDTO> products = productService.getProducts();

        model.addAttribute("products", products);

        return "shop/product.html";
    }

    @GetMapping("/shop/detail")
    public String detail() {

        return "shop/product-detail.html";
    }
}