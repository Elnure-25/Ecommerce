package az.itbtechno.ecommerce.repostories;

import az.itbtechno.ecommerce.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByProductIdAndUserId(Long id, Object productId);
}
