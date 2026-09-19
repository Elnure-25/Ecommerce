package az.itbtechno.ecommerce.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="categories")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "boolean default false")
    private boolean pinned;

    @OneToMany(mappedBy = "Category")
    List<Product> products = new ArrayList<>();


}
