package az.itbtechno.ecommerce.controllers;

import az.itbtechno.ecommerce.dto.cart.CartCreateDTO;
import az.itbtechno.ecommerce.dto.cart.CartUserDTO;
import az.itbtechno.ecommerce.dto.product.ProductDashboardDTO;
import az.itbtechno.ecommerce.dto.product.ProductDetailDTO;
import az.itbtechno.ecommerce.services.CartService;
import az.itbtechno.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;
    private final CartService cartService;


    @GetMapping("/shop")
    public String index(Model model) {

        List<ProductDashboardDTO> products =
                productService.getProducts();

        model.addAttribute("products", products);

        return "shop/product.html";
    }


    @GetMapping("/shop/detail/{id}")
    public String detail(@PathVariable Long id,
                         Model model) {

        ProductDetailDTO product =
                productService.getProductDetailById(id);

        model.addAttribute("product", product);

        return "shop/product-detail.html";
    }


    @GetMapping("/cart")
    @PreAuthorize("isAuthenticated()")
    public String cart(Principal principal,
                       Model model) {

        String email = principal.getName();

        List<CartUserDTO> carts =
                cartService.getUserCart(email);

        model.addAttribute("carts", carts);

        return "cart/shop-cart.html";
    }


    @PostMapping("/cart")
    @PreAuthorize("isAuthenticated()")
    public String addToCart(Principal principal,
                            CartCreateDTO cartCreateDTO) {

        String email = principal.getName();

        cartService.createCartItem(
                email,
                cartCreateDTO
        );

        return "redirect:/cart";
    }


    @PostMapping("/cart/update")
    @PreAuthorize("isAuthenticated()")
    public String updateCart(
            @RequestParam Long cartId,
            @RequestParam int quantity) {

        cartService.updateQuantity(
                cartId,
                quantity
        );

        return "redirect:/cart";
    }


    @GetMapping("/cart/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteCart(
            @PathVariable Long id) {

        cartService.deleteCartItem(id);

        return "redirect:/cart";
    }
}

