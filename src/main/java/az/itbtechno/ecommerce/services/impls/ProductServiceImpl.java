package az.itbtechno.ecommerce.services.impls;

import az.itbtechno.ecommerce.dto.product.ProductCreateDTO;
import az.itbtechno.ecommerce.dto.product.ProductDashboardDTO;
import az.itbtechno.ecommerce.dto.product.ProductHotTrendDTO;
import az.itbtechno.ecommerce.dto.product.ProductUpdateDTO;
import az.itbtechno.ecommerce.models.Brand;
import az.itbtechno.ecommerce.models.Category;
import az.itbtechno.ecommerce.models.Product;
import az.itbtechno.ecommerce.payloads.PaginationPayload;
import az.itbtechno.ecommerce.repostories.BrandRepository;
import az.itbtechno.ecommerce.repostories.CategoryRepository;
import az.itbtechno.ecommerce.repostories.ProductRepository;
import az.itbtechno.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public PaginationPayload<ProductDashboardDTO> getDashboardProducts(int size,int currentPage) {

        Pageable filter = PageRequest.of(currentPage, size, Sort.by("id").descending());

        Page<Product> products = productRepository.findAll(filter);

        List<ProductDashboardDTO> productDTOs = products.getContent().stream().map(product -> {
            ProductDashboardDTO dto = modelMapper.map(product, ProductDashboardDTO.class);

                    if (product.getBrand() != null) {
                        dto.setBrand(modelMapper.map(product.getBrand(),az.itbtechno.ecommerce.dto.brand.BrandDTO.class));
                    }

                    if (product.getCategory() != null) {dto.setCategory(modelMapper.map(product.getCategory(),az.itbtechno.ecommerce.dto.category.CategoryDTO.class));
                    }
                    return dto;
                }).toList();

        return new PaginationPayload<>(size, currentPage, productDTOs);
    }


    @Override
    public void createProduct(ProductCreateDTO productCreateDTO) {

        Product product = modelMapper.map(productCreateDTO,Product.class);

        Brand brand =brandRepository.findById(productCreateDTO.getBrandId()).orElseThrow(() -> new RuntimeException("Brand not found"));

        Category category = categoryRepository.findById(productCreateDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

        product.setBrand(brand);
        product.setCategory(category);

        productRepository.save(product);
    }


    @Override
    public ProductUpdateDTO getUpdatedProduct(Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        ProductUpdateDTO dto = new ProductUpdateDTO();

        dto.setName(product.getName());
        dto.setQuantity(product.getQuantity());
        dto.setPrice(product.getPrice());
        dto.setDiscountPrice(product.getDiscountPrice());
        dto.setDescription(product.getDescription());
        dto.setShortDescription(product.getShortDescription());
        dto.setSpecification(product.getSpecification());

        // Trend / Best Seller / Feature
        dto.setTrend(product.isTrend());
        dto.setBestSeller(product.isBestSeller());
        dto.setFeature(product.isFeature());

        if (product.getBrand() != null) {
            dto.setBrandId(
                    product.getBrand().getId()
            );
        }

        if (product.getCategory() != null) {
            dto.setCategoryId(
                    product.getCategory().getId()
            );
        }

        return dto;
    }


    @Override
    public void UpdatedProduct(
            Long id,
            ProductUpdateDTO productUpdateDTO) {

        Product product =productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productUpdateDTO.getName());
        product.setQuantity(productUpdateDTO.getQuantity());
        product.setPrice(productUpdateDTO.getPrice());
        product.setDiscountPrice(productUpdateDTO.getDiscountPrice());
        product.setDescription(productUpdateDTO.getDescription());
        product.setShortDescription(productUpdateDTO.getShortDescription());
        product.setSpecification(productUpdateDTO.getSpecification());

        product.setTrend(productUpdateDTO.isTrend());
        product.setBestSeller(productUpdateDTO.isBestSeller());
        product.setFeature(productUpdateDTO.isFeature());

        Brand brand = brandRepository.findById(productUpdateDTO.getBrandId()).orElseThrow(() -> new RuntimeException("Brand not found"));

        Category category = categoryRepository.findById(productUpdateDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

        product.setBrand(brand);
        product.setCategory(category);
        productRepository.save(product);
    }


    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public List<ProductHotTrendDTO>getHotTrends(){
       List<Product>products = productRepository.findByTrendTrueOrderById();

       List<ProductHotTrendDTO> productHotTrendDTOList=products.stream().map(product -> modelMapper.map(product,ProductHotTrendDTO.class)).toList();
       return List.of();
    }
}
