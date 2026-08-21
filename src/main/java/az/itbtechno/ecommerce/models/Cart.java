package az.itbtechno.ecommerce.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name="carts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Product product;
    @ManyToOne
    Color color;
    @ManyToOne
    Size size;


    @ManyToOne
    User user;

    int quantity;



}