package az.itbtechno.ecommerce.dto.category;


import az.itbtechno.ecommerce.dto.product.ProductCategoryDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDTO {
    Long id;
    String name;


}
