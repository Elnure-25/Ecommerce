package az.itbtechno.ecommerce.controllers.dashboard;

import az.itbtechno.ecommerce.dto.brand.BrandDTO;
import az.itbtechno.ecommerce.dto.category.CategoryDTO;
import az.itbtechno.ecommerce.dto.product.ProductCreateDTO;
import az.itbtechno.ecommerce.dto.product.ProductDashboardDTO;
import az.itbtechno.ecommerce.dto.product.ProductUpdateDTO;
import az.itbtechno.ecommerce.payloads.PaginationPayload;
import az.itbtechno.ecommerce.services.BrandService;
import az.itbtechno.ecommerce.services.CategoryService;
import az.itbtechno.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;


    // GET ALL PRODUCTS

    @GetMapping
    public String getAll(
            Model model,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int pageNumber) {

        PaginationPayload<ProductDashboardDTO> productList =
                productService.getDashboardProducts(size, pageNumber);

        model.addAttribute("product", productList);

        return "admin/product/index.html";
    }


    // CREATE PRODUCT - GET

    @GetMapping("/create")
    public String create(Model model) {

        List<BrandDTO> brandList =
                brandService.getBrandList();

        List<CategoryDTO> categoryList =
                categoryService.getPinnedCategoryList();

        model.addAttribute(
                "productCreateDTO",
                new ProductCreateDTO()
        );

        model.addAttribute(
                "brand",
                brandList
        );

        model.addAttribute(
                "categories",
                categoryList
        );

        return "admin/product/create.html";
    }
    

    @PostMapping("/create")
    public String create(
            @Valid ProductCreateDTO productCreateDTO,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "brand",
                    brandService.getBrandList()
            );

            model.addAttribute(
                    "categories",
                    categoryService.getPinnedCategoryList()
            );

            return "admin/product/create.html";
        }

        productService.createProduct(productCreateDTO);

        return "redirect:/dashboard/product";
    }


    // UPDATE PRODUCT - GET

    @GetMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            Model model) {

        List<BrandDTO> brandList =
                brandService.getBrandList();

        List<CategoryDTO> categoryList =
                categoryService.getCategoryList();

        ProductUpdateDTO productUpdateDTO =
                productService.getUpdatedProduct(id);

        model.addAttribute(
                "productUpdateDTO",
                productUpdateDTO
        );

        model.addAttribute(
                "productId",
                id
        );

        model.addAttribute(
                "brand",
                brandList
        );

        model.addAttribute(
                "categories",
                categoryList
        );

        return "admin/product/update.html";
    }


    @PostMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            @Valid ProductUpdateDTO productUpdateDTO,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "brand",
                    brandService.getBrandList()
            );

            model.addAttribute(
                    "categories",
                    categoryService.getCategoryList()
            );

            model.addAttribute(
                    "productId",
                    id
            );

            return "admin/product/update.html";
        }

        productService.UpdatedProduct(
                id,
                productUpdateDTO
        );

        return "redirect:/dashboard/product";
    }


    @GetMapping("/delete/{id}")
    public String deletePage(
            @PathVariable Long id,
            Model model) {

        ProductUpdateDTO productUpdateDTO =
                productService.getUpdatedProduct(id);

        model.addAttribute(
                "productUpdateDTO",
                productUpdateDTO
        );

        model.addAttribute(
                "productId",
                id
        );

        return "admin/product/delete.html";
    }

    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return "redirect:/dashboard/product";
    }
}