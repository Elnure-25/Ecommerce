package az.itbtechno.ecommerce.repostories;

import az.itbtechno.ecommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByPinnedTrueOrderById();
}