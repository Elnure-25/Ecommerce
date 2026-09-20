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
    BigDecimal resultPrice;
    int quantity;
    BigDecimal totalPrice;

    public BigDecimal getResultPrice(){
        BigDecimal result = product.getDiscountPrice() != BigDecimal.valueOf(0) ? product.getDiscountPrice() : product.getPrice();

        return result;
    }

    public BigDecimal getTotalPrice(){
        return product.getPrice().multiply(this.resultPrice);
    }
}
