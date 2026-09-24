package az.itbtechno.ecommerce.services;

import az.itbtechno.ecommerce.dto.cart.CartCreateDTO;
import az.itbtechno.ecommerce.dto.cart.CartUserDTO;

import java.util.List;

public interface CartService {

    void createCartItem(String email, CartCreateDTO cartCreateDTO);

    List<CartUserDTO> getUserCart(String email);

    void updateQuantity(Long cartId, int quantity);

    void deleteCartItem(Long cartId);
}

