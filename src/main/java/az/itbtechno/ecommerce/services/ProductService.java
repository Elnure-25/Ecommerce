package az.itbtechno.ecommerce.services;

import az.itbtechno.ecommerce.dto.product.*;
import az.itbtechno.ecommerce.models.Product;
import az.itbtechno.ecommerce.payloads.PaginationPayload;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {

    PaginationPayload<ProductDashboardDTO> getDashboardProducts(int size, int currentPage);

    void createProduct(@Valid ProductCreateDTO productCreateDTO);

    ProductUpdateDTO getUpdatedProduct(Long id);

    void UpdatedProduct(Long id, @Valid ProductUpdateDTO productUpdateDTO);

    void deleteProduct(Long id);

    List<ProductHotTrendDTO> getHotTrends();

    List<ProductHotTrendDTO> getBestSellers();

    List<ProductHotTrendDTO> getFeatures();

    List<ProductDashboardDTO> getProducts();

    ProductDetailDTO getProductDetailById(Long id);

    Product findProductById(Object productId);

    Product findProductById(Long productId);
}