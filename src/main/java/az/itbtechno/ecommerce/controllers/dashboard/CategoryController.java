package az.itbtechno.ecommerce.controllers.dashboard;

import az.itbtechno.ecommerce.dto.category.CategoryCreateDTO;
import az.itbtechno.ecommerce.dto.category.CategoryDashboardDTO;
import az.itbtechno.ecommerce.dto.category.CategoryUpdateDTO;
import az.itbtechno.ecommerce.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String getAll(Model model) {

        List<CategoryDashboardDTO> categoryList =
                categoryService.getDashboardCategories();

        model.addAttribute("categories", categoryList);

        return "admin/category/index.html";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute(
                "categoryCreateDTO",
                new CategoryCreateDTO()
        );

        return "admin/category/create.html";
    }

    @PostMapping("/create")
    public String create(
            @Valid CategoryCreateDTO categoryCreateDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            return "admin/category/create.html";
        }

        categoryService.createCategory(categoryCreateDTO);

        return "redirect:/dashboard/category";
    }

    @GetMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            Model model) {

        CategoryUpdateDTO categoryUpdateDTO =
                categoryService.getUpdatedCategory(id);

        model.addAttribute(
                "categoryUpdateDTO",
                categoryUpdateDTO
        );

        model.addAttribute(
                "categoryId",
                id
        );

        return "admin/category/update.html";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            @Valid CategoryUpdateDTO categoryUpdateDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            return "admin/category/update.html";
        }

        categoryService.UpdatedCategory(
                id,
                categoryUpdateDTO
        );

        return "redirect:/dashboard/category";
    }

    @GetMapping("/delete/{id}")
    public String deletePage(
            @PathVariable Long id,
            Model model) {

        CategoryUpdateDTO categoryUpdateDTO =
                categoryService.getUpdatedCategory(id);

        model.addAttribute(
                "categoryUpdateDTO",
                categoryUpdateDTO
        );

        model.addAttribute(
                "categoryId",
                id
        );

        return "admin/category/delete.html";
    }

    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        return "redirect:/dashboard/category";
    }
}