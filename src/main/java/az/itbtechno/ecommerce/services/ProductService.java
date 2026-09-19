package az.itbtechno.ecommerce.services;

import az.itbtechno.ecommerce.dto.product.ProductCreateDTO;
import az.itbtechno.ecommerce.dto.product.ProductDashboardDTO;
import az.itbtechno.ecommerce.dto.product.ProductHotTrendDTO;
import az.itbtechno.ecommerce.dto.product.ProductUpdateDTO;
import az.itbtechno.ecommerce.payloads.PaginationPayload;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {

    PaginationPayload<ProductDashboardDTO>getDashboardProducts(int size,int currentPage);
    void createProduct(@Valid ProductCreateDTO productCreateDTO);

    ProductUpdateDTO getUpdatedProduct(Long id);

    void UpdatedProduct(Long id, @Valid ProductUpdateDTO productUpdateDTO);

    void deleteProduct(Long id);

    List<ProductHotTrendDTO>getHotTrends();
}
