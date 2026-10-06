package com.project.code.Service;

import org.springframework.stereotype.Service;

import com.project.code.Model.Inventory;
import com.project.code.Model.Product;
import com.project.code.Repository.InventoryRepository;
import com.project.code.Repository.ProductRepository;

import java.util.Optional;

@Service
public class ServiceClass {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public ServiceClass(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public boolean validateInventory(Inventory inventory) {
        Inventory result = inventoryRepository.findByProductIdAndStoreId(
                inventory.getProduct().getId(),
                inventory.getStore().getId()
        );

        if (result != null) {
            return false;
        }

        return true;
    }

    public boolean validateProduct(Product product) {
        Product result = productRepository.findByName(product.getName());

        if (result != null) {
            return false;
        }

        return true;
    }

    public boolean ValidateProductId(long id) {
        Optional<Product> result = productRepository.findById(id);

        System.out.println(result);

        if (result.isEmpty()) {
            return false;
        }

        return true;
    }

    public Inventory getInventoryId(Inventory inventory) {
        Inventory result = inventoryRepository.findByProductIdAndStoreId(
                inventory.getProduct().getId(),
                inventory.getStore().getId()
        );

        return result;
    }
}