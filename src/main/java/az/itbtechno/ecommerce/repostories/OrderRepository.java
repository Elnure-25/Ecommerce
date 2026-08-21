package az.itbtechno.ecommerce.repostories;

import az.itbtechno.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
