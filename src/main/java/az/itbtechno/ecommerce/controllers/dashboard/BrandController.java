package az.itbtechno.ecommerce.controllers.dashboard;

import az.itbtechno.ecommerce.dto.brand.BrandCreateDTO;
import az.itbtechno.ecommerce.dto.brand.BrandDashboardDTO;
import az.itbtechno.ecommerce.dto.brand.BrandUpdateDTO;
import az.itbtechno.ecommerce.services.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public String getAll(Model model) {

        List<BrandDashboardDTO> brandList =
                brandService.getDashboardBrands();

        model.addAttribute("brands", brandList);

        return "admin/brand/index.html";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute(
                "brandCreateDTO",
                new BrandCreateDTO()
        );

        return "admin/brand/create.html";
    }

    @PostMapping("/create")
    public String create(
            @Valid BrandCreateDTO brandCreateDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            return "admin/brand/create.html";
        }

        brandService.createBrand(brandCreateDTO);

        return "redirect:/dashboard/brand";
    }

    @GetMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            Model model) {

        BrandUpdateDTO brandUpdateDTO =
                brandService.getUpdatedBrand(id);

        model.addAttribute(
                "brandUpdateDTO",
                brandUpdateDTO
        );

        model.addAttribute(
                "brandId",
                id
        );

        return "admin/brand/update.html";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            @Valid BrandUpdateDTO brandUpdateDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            return "admin/brand/update.html";
        }

        brandService.UpdateBrand(
                id,
                brandUpdateDTO
        );

        return "redirect:/dashboard/brand";
    }

    @GetMapping("/delete/{id}")
    public String deletePage(
            @PathVariable Long id,
            Model model) {

        BrandUpdateDTO brandUpdateDTO =
                brandService.getUpdatedBrand(id);

        model.addAttribute(
                "brandUpdateDTO",
                brandUpdateDTO
        );

        model.addAttribute(
                "brandId",
                id
        );

        return "admin/brand/delete.html";
    }

    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id) {

        brandService.deleteBrand(id);

        return "redirect:/dashboard/brand";
    }
}