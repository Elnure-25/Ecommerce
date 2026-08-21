package az.itbtechno.ecommerce.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    String shortDescription;
    String specification;
    int quantity;


    BigDecimal price;
    BigDecimal discountPrice;

@ManyToOne
    Brand brand;

@OneToMany(mappedBy = "product")
    List<ProductSize>productSizes = new ArrayList<>();

@OneToMany(mappedBy = "product")
    List<Photo> photos = new ArrayList<>();



}
