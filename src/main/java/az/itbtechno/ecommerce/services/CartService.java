package az.itbtechno.ecommerce.services;

import az.itbtechno.ecommerce.dto.cart.CartCreateDTO;

public interface CartService {
    void createCartItem(String email, CartCreateDTO cartCreateDTO);
}
