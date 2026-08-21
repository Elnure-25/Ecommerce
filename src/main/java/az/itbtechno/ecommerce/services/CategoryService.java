package az.itbtechno.ecommerce.services;

import az.itbtechno.ecommerce.dto.response.category.CategoryHomeDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryHomeDTO> categoryList();

}
