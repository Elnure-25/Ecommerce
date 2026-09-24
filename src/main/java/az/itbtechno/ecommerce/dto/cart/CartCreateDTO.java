package az.itbtechno.ecommerce.dto.cart;

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
public class CartCreateDTO {

    Long productId;

    String staticProductName;

    BigDecimal staticProductPrice;

    int quantity = 1;
}