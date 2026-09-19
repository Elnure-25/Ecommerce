package az.itbtechno.ecommerce.dto.product;

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
public class ProductCreateDTO {
    Long id;
    String name;
    int quantity;

    BigDecimal price;
    BigDecimal discountPrice;

    String description;
    String shortDescription;
    String specification;

    Long categoryId;
    Long brandId;

    boolean trend;
    boolean bestSeller;
    boolean feature;

}
