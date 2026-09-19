package az.itbtechno.ecommerce.services.impls;

import az.itbtechno.ecommerce.dto.brand.BrandCreateDTO;
import az.itbtechno.ecommerce.dto.brand.BrandDTO;
import az.itbtechno.ecommerce.dto.brand.BrandDashboardDTO;
import az.itbtechno.ecommerce.dto.brand.BrandUpdateDTO;
import az.itbtechno.ecommerce.models.Brand;
import az.itbtechno.ecommerce.repostories.BrandRepository;
import az.itbtechno.ecommerce.services.BrandService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<BrandDashboardDTO> getDashboardBrands() {

       List<Brand>brands = brandRepository.findAll();

       if(!brands.isEmpty()){
           List<BrandDashboardDTO> brandDashboardDTOList =
                   brands.stream()
                           .map(brand -> modelMapper.map(brand, BrandDashboardDTO.class))
                           .toList();

           return brandDashboardDTOList;
       }
        return List.of();
    }

    @Override
    public void createBrand(BrandCreateDTO brandCreateDTO) {

        Brand brand = new Brand();
        brand.setName(brandCreateDTO.getName());
        brandRepository.save(brand);
    }

    @Override
    public BrandUpdateDTO getUpdatedBrand(Long id) {
        Brand findBrand = brandRepository.findById(id).orElseThrow();
        BrandUpdateDTO brandUpdateDTO= modelMapper.map(findBrand,BrandUpdateDTO.class);
        return brandUpdateDTO;
    }

    @Override
    public void UpdateBrand(Long id, BrandUpdateDTO brandUpdateDTO) {

        Brand findBrand = brandRepository.findById(id).orElseThrow();

        findBrand.setName(brandUpdateDTO.getName());
        brandRepository.save(findBrand);
    }

    @Override
    public BrandUpdateDTO getUpdateBrand(Long id) {
        return null;
    }

    @Override
    public void deleteBrand(Long id) {

        brandRepository.deleteById(id);
    }

    @Override
    public List<BrandDTO> getBrandList() {
        List<Brand>brands = brandRepository.findAll();

        if(!brands.isEmpty()) {
            List<BrandDTO> brandDTOS =
                    brands.stream()
                            .map(brand -> modelMapper.map(brand, BrandDTO.class))
                            .toList();

            return brandDTOS;
        }
        return List.of();
    }
}