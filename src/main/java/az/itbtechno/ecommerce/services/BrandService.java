package az.itbtechno.ecommerce.services;

import java.util.List;
import az.itbtechno.ecommerce.dto.brand.BrandCreateDTO;
import az.itbtechno.ecommerce.dto.brand.BrandDTO;
import az.itbtechno.ecommerce.dto.brand.BrandUpdateDTO;
import jakarta.validation.Valid;
import az.itbtechno.ecommerce.dto.brand.BrandDashboardDTO;
public interface BrandService {
    List<BrandDashboardDTO> getDashboardBrands();

    void createBrand(@Valid BrandCreateDTO brandCreateDTO);

    BrandUpdateDTO getUpdatedBrand(Long id);

    void UpdateBrand(Long id, @Valid BrandUpdateDTO brandUpdateDTO);

    BrandUpdateDTO getUpdateBrand(Long id);

    void deleteBrand(Long id);

    List<BrandDTO> getBrandList();
}
