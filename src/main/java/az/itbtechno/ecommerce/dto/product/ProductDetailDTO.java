package az.itbtechno.ecommerce.dto.product;


import az.itbtechno.ecommerce.dto.brand.BrandDTO;
import az.itbtechno.ecommerce.dto.category.CategoryDTO;
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
public class ProductDetailDTO {

    Long id;
    String name;
    int quantity;

    BigDecimal price;
    BigDecimal discountPrice;

    BrandDTO brand;
    CategoryDTO category;

    String description;
    String shortDescription;
    String specification;
}
