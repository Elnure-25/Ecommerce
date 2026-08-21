package az.itbtechno.ecommerce.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Entity
@Table(name="coupons")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    int quantity;
    Date deadLine;



}
