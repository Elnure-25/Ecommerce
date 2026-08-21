package az.itbtechno.ecommerce.repostories;

import az.itbtechno.ecommerce.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
