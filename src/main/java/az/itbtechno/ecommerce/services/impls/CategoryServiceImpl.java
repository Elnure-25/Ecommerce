package az.itbtechno.ecommerce.services.impls;

import az.itbtechno.ecommerce.dto.category.CategoryCreateDTO;
import az.itbtechno.ecommerce.dto.category.CategoryDashboardDTO;
import az.itbtechno.ecommerce.dto.category.CategoryHomeDTO;
import az.itbtechno.ecommerce.dto.category.CategoryUpdateDTO;
import az.itbtechno.ecommerce.models.Category;
import az.itbtechno.ecommerce.repostories.CategoryRepository;
import az.itbtechno.ecommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<CategoryHomeDTO> categoryList() {

        List<Category> categories = categoryRepository.findAll();
        List<CategoryHomeDTO> categoryHomeDTOList = categories.stream()
                .map(category -> modelMapper.map(category, CategoryHomeDTO.class))
                .toList();

        return categoryHomeDTOList;
    }

    @Override
    public List<CategoryDashboardDTO> getDashboardCategories() {

        List<Category> categories = categoryRepository.findAll();

        if (!categories.isEmpty()) {

            List<CategoryDashboardDTO> categoryDashboardDTOList = categories
                    .stream()
                    .map(category -> modelMapper.map(category, CategoryDashboardDTO.class))
                    .toList();

            return categoryDashboardDTOList;
        }

        return List.of();
    }

    @Override
    public void createCategory(CategoryCreateDTO categoryCreateDTO) {

        Category category= new Category();
        category.setName(categoryCreateDTO.getName());
        categoryRepository.save(category);

    }

    @Override
    public CategoryUpdateDTO getUpdatedCategory(Long id) {
       Category findCategory= categoryRepository.findById(id).orElseThrow();
        CategoryUpdateDTO category=modelMapper.map(findCategory,CategoryUpdateDTO.class);

       return category;
    }

    @Override
    public void UpdatedCategory(Long id,CategoryUpdateDTO categoryUpdateDTO) {
        Category findCategory = categoryRepository.findById(id).orElseThrow();
        findCategory.setName(categoryUpdateDTO.getName());
        categoryRepository.save(findCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}