package az.itbtechno.ecommerce.services.impls;

import az.itbtechno.ecommerce.dto.response.category.CategoryHomeDTO;
import az.itbtechno.ecommerce.models.Category;
import az.itbtechno.ecommerce.repostories.CategoryRepository;
import az.itbtechno.ecommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
private final ModelMapper modelMapper;
    @Override
    public List<CategoryHomeDTO> categoryList() {

        List<Category> categories = categoryRepository.findAll();

        List<CategoryHomeDTO> categoryHomeDTOList =
                categories.stream()
                        .map(category -> modelMapper.map(category, CategoryHomeDTO.class))
                        .toList();


        return categoryHomeDTOList;
    }

    }

