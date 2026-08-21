package az.itbtechno.ecommerce.repostories;

import az.itbtechno.ecommerce.models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
}
