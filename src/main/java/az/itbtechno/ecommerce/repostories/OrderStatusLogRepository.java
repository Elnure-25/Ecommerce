package az.itbtechno.ecommerce.repostories;

import az.itbtechno.ecommerce.models.Order;
import az.itbtechno.ecommerce.models.OrderStatusLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusLogRepository extends JpaRepository<OrderStatusLog,Long> {
}
