package az.itbtechno.ecommerce.dto.cart;

import az.itbtechno.ecommerce.dto.product.ProductDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartUserDTO {

    Long id;

    ProductDTO product;

    String staticProductName;

    BigDecimal staticProductPrice;

    BigDecimal resultPrice;

    int quantity;

    BigDecimal totalPrice;


    public String getDisplayName() {

        if (product != null) {
            return product.getName();
        }

        return staticProductName;
    }


    public BigDecimal getResultPrice() {

        // DB product
        if (product != null) {

            if (product.getDiscountPrice() != null
                    && product.getDiscountPrice()
                    .compareTo(BigDecimal.ZERO) > 0) {

                return product.getDiscountPrice();
            }

            if (product.getPrice() != null) {
                return product.getPrice();
            }
        }

        // Static home product
        if (staticProductPrice != null) {
            return staticProductPrice;
        }

        // Heç bir qiymət yoxdursa
        return BigDecimal.ZERO;
    }


    public BigDecimal getTotalPrice() {

        return getResultPrice()
                .multiply(
                        BigDecimal.valueOf(quantity)
                );
    }
}

