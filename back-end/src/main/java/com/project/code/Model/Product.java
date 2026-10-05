package com.project.code.Model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Product", uniqueConstraints = @UniqueConstraint(columnNames = "sku"))
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Product-name cannot be empty")
    private String name;

    @NotNull(message = "Category cannot be empty")
    private String category;

    @NotNull(message = "Price cannot be empty")
    private Double price;

    @NotNull
    private String sku;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference("inventory-product")
    private Inventory inventory;

}


