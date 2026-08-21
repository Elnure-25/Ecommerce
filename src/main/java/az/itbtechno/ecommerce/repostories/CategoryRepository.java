package az.itbtechno.ecommerce.repostories;

import az.itbtechno.ecommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
