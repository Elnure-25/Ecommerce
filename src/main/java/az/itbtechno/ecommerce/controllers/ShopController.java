package az.itbtechno.ecommerce.controllers;

import az.itbtechno.ecommerce.dto.cart.CartCreateDTO;
import az.itbtechno.ecommerce.dto.product.ProductDashboardDTO;
import az.itbtechno.ecommerce.services.CartService;
import az.itbtechno.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;
    private final CartService cartService;


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


    @GetMapping("/cart")
    @PreAuthorize("isAuthenticated()")
    public String cart(Principal principal) {

        String email = principal.getName();

        return "cart/shop-cart.html";
    }


    @PostMapping("/cart")
    @PreAuthorize("isAuthenticated()")
    public String addToCart(Principal principal,
                            CartCreateDTO cartCreateDTO) {

        String email = principal.getName();

        cartService.createCartItem(email, cartCreateDTO);

        return "redirect:/cart";
    }
}

