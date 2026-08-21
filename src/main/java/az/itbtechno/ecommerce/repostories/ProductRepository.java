package az.itbtechno.ecommerce.repostories;

import az.itbtechno.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
