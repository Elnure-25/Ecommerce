package az.itbtechno.ecommerce.models;

import az.itbtechno.ecommerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name="order_status_logs")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class OrderStatusLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    OrderStatus orderStatus;
    @ManyToOne
    Order order;
    LocalDateTime statusUpdatedDate;
    String message;


}
