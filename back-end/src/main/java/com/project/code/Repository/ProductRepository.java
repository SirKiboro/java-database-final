package com.project.code.Repository;


import com.project.code.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    public Product findByid();

    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    Product findBySku(String Sku);

    Product findByName(String name);

    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND i.product.category = :category")
    List<Product> findByNameLike(
            @Param("storeId") Long storeId, @Param("pName") String pName
    );

    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND LOWER(i.product.name) LIKE LOWER(CONCAT('%', :pName, '%')) AND i.product.category = :category")
    List<Product> findByNameAndCategory(Long storeId, String pName, String category);

    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND i.product.category = :category")
    List<Product> findByCategoryAndStoreId(Long storeId, String category);

    @Query("SELECT i FROM Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pName, '%'))")
    List<Product> findProductBySubName(String pName);

    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId")
    List<Product> findProductsByStoreId(Long storeId);

    @Query("SELECT i.product FROM Inventory i WHERE i.product.category = :category and i.store.id = :storeId")
    List<Product> findProductByCategory(String category, Long storeId);

    @Query("SELECT i FROM Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pName, '%')) AND i.category = :category")
    List<Product> findProductBySubNameAndCategory(String pName, String category);

}
