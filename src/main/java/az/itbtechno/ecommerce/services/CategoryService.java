package az.itbtechno.ecommerce.services;

import az.itbtechno.ecommerce.dto.category.CategoryCreateDTO;
import az.itbtechno.ecommerce.dto.category.CategoryDashboardDTO;
import az.itbtechno.ecommerce.dto.category.CategoryHomeDTO;
import az.itbtechno.ecommerce.dto.category.CategoryUpdateDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryHomeDTO> categoryList();

    List<CategoryDashboardDTO> getDashboardCategories();

    void createCategory(CategoryCreateDTO categoryCreateDTO);

    CategoryUpdateDTO getUpdatedCategory(Long id);

    void UpdatedCategory(Long id,CategoryUpdateDTO categoryUpdateDTO);

    void deleteCategory(Long id);
}
