package az.itbtechno.ecommerce.services.impls;

import az.itbtechno.ecommerce.dto.cart.CartCreateDTO;
import az.itbtechno.ecommerce.dto.cart.CartUserDTO;
import az.itbtechno.ecommerce.dto.product.ProductDTO;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ProductService productService;


    @Override
    public void createCartItem(
            String email,
            CartCreateDTO cartCreateDTO) {

        User findUser =
                userService.findUserByEmail(email);


        /*
         * DATABASE PRODUCT
         */
        if (cartCreateDTO.getProductId() != null) {

            Product findProduct =
                    productService.findProductById(
                            cartCreateDTO.getProductId()
                    );

            Cart findCart =
                    cartRepository.findByProductIdAndUserId(
                            cartCreateDTO.getProductId(),
                            findUser.getId()
                    );


            if (findCart != null) {

                findCart.setQuantity(
                        findCart.getQuantity()
                                + cartCreateDTO.getQuantity()
                );

                cartRepository.save(findCart);

                return;
            }


            Cart cart = new Cart();

            cart.setQuantity(
                    cartCreateDTO.getQuantity()
            );

            cart.setUser(findUser);

            cart.setProduct(findProduct);

            cartRepository.save(cart);

            return;
        }


        /*
         * STATIC ASHION PRODUCT
         */
        Cart findCart =
                cartRepository.findByStaticProductNameAndUserId(
                        cartCreateDTO.getStaticProductName(),
                        findUser.getId()
                );


        if (findCart != null) {

            findCart.setQuantity(
                    findCart.getQuantity()
                            + cartCreateDTO.getQuantity()
            );

            cartRepository.save(findCart);

            return;
        }


        Cart cart = new Cart();

        cart.setUser(findUser);

        cart.setQuantity(
                cartCreateDTO.getQuantity()
        );

        cart.setStaticProductName(
                cartCreateDTO.getStaticProductName()
        );

        cart.setStaticProductPrice(
                cartCreateDTO.getStaticProductPrice()
        );

        cartRepository.save(cart);
    }


    @Override
    public List<CartUserDTO> getUserCart(String email) {

        User user =
                userService.findUserByEmail(email);


        List<Cart> carts =
                cartRepository.findAll()
                        .stream()
                        .filter(cart ->
                                cart.getUser()
                                        .getId()
                                        .equals(user.getId())
                        )
                        .toList();


        return carts.stream()
                .map(this::convertToCartUserDTO)
                .toList();
    }


    private CartUserDTO convertToCartUserDTO(Cart cart) {

        CartUserDTO dto = new CartUserDTO();

        dto.setId(cart.getId());

        dto.setQuantity(cart.getQuantity());


        /*
         * DATABASE PRODUCT
         */
        if (cart.getProduct() != null) {

            ProductDTO productDTO =
                    modelMapper.map(
                            cart.getProduct(),
                            ProductDTO.class
                    );

            dto.setProduct(productDTO);
        }


        /*
         * STATIC PRODUCT
         */
        dto.setStaticProductName(
                cart.getStaticProductName()
        );

        dto.setStaticProductPrice(
                cart.getStaticProductPrice()
        );


        return dto;
    }


    @Override
    public void updateQuantity(
            Long cartId,
            int quantity) {

        Cart cart =
                cartRepository.findById(cartId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cart item not found"
                                )
                        );


        if (quantity <= 0) {

            cartRepository.delete(cart);

            return;
        }


        cart.setQuantity(quantity);

        cartRepository.save(cart);
    }


    @Override
    public void deleteCartItem(Long cartId) {

        Cart cart =
                cartRepository.findById(cartId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cart item not found"
                                )
                        );

        cartRepository.delete(cart);
    }
}

