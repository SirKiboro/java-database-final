package com.project.code.Repository;


import com.project.code.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    Inventory findByProductIdAndStoreId(Long productId, Long storeId);

    List<Inventory> findByStore_Id(Long storeId);

    @Modifying
    @Transactional
    void deleteProductById(Long productId);


}
