package az.itbtechno.ecommerce.repostories;

import az.itbtechno.ecommerce.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo,Long> {
}
