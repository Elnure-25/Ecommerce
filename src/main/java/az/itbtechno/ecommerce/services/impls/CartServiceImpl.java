package az.itbtechno.ecommerce.services.impls;

import az.itbtechno.ecommerce.dto.cart.CartCreateDTO;
import az.itbtechno.ecommerce.models.Cart;
import az.itbtechno.ecommerce.models.Product;
import az.itbtechno.ecommerce.models.User;
import az.itbtechno.ecommerce.repostories.CartRepository;
import az.itbtechno.ecommerce.services.CartService;
import az.itbtechno.ecommerce.services.ProductService;
import az.itbtechno.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ProductService productService;
    @Override
    public void createCartItem(String email, CartCreateDTO cartCreateDTO) {


        User findUser = userService.findUserByEmail(email);

        Product findProduct = productService.findProductById(cartCreateDTO.getProductId());

        Cart findCart = cartRepository.findByProductIdAndUserId(findUser.getId(),cartCreateDTO.getProductId());

        if(findCart !=null){
            findCart.setQuantity(cartCreateDTO.getQuantity()+findCart.getQuantity());

            cartRepository.save(findCart);
        }
        Cart cart = new Cart();
        cart.setQuantity(cartCreateDTO.getQuantity());
        cart.setUser(findUser);
        cart.setProduct(findProduct);
        cartRepository.save(findCart);
    }
}
