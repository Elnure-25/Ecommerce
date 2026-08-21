package az.itbtechno.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor

public class ShopController {
    @GetMapping("/shop")
    public String index(){
        return"shop/product.html";
    }
    @GetMapping("/shop/detail/{id}")
    public String detail(@PathVariable Long id){
        return "shop/product-detail.html";
    }
}
