package az.itbtechno.ecommerce.models;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@Entity
@Table(name="product_sizes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Product product;
    @ManyToOne
    Color color;
    @ManyToOne
    Size size;
    int quantity;

}
