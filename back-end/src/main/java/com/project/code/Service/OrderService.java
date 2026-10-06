package com.project.code.Service;

import com.project.code.Model.Customer;
import com.project.code.Model.Inventory;
import com.project.code.Model.OrderDetails;
import com.project.code.Model.OrderItem;
import com.project.code.Model.Product;
import com.project.code.Model.Store;
import com.project.code.Repository.CustomerRepository;
import com.project.code.Repository.InventoryRepository;
import com.project.code.Repository.OrderDetailsRepository;
import com.project.code.Repository.OrderItemRepository;
import com.project.code.Repository.ProductRepository;
import com.project.code.Repository.StoreRepository;
import com.project.code.dto.PlaceOrderRequestDTO;
import com.project.code.dto.PurchaseProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void saveOrder(PlaceOrderRequestDTO placeOrderRequest) {

        // Retrieve or create customer
        Customer customer = customerRepository.findByEmail(
                placeOrderRequest.getCustomerEmail()
        );

        if (customer == null) {
            customer = new Customer();

            customer.setName(placeOrderRequest.getCustomerName());
            customer.setEmail(placeOrderRequest.getCustomerEmail());
            customer.setPhone(placeOrderRequest.getCustomerPhone());

            customer = customerRepository.save(customer);
        }

        // Retrieve store
        Store store = storeRepository.findById(
                placeOrderRequest.getStoreId()
        ).orElseThrow(() -> new RuntimeException("Store not found"));

        // Create order details
        OrderDetails orderDetails = new OrderDetails();

        orderDetails.setCustomer(customer);
        orderDetails.setStore(store);
        orderDetails.setTotalPrice(placeOrderRequest.getTotalPrice());
        orderDetails.setDate(LocalDateTime.now());

        orderDetails = orderDetailsRepository.save(orderDetails);

        // Create and save order items
        List<PurchaseProductDTO> products =
                placeOrderRequest.getPurchaseProduct();

        for (PurchaseProductDTO productDTO : products) {

            // Retrieve product
            Product product = productRepository.findById(
                    productDTO.getId()
            ).orElseThrow(() -> new RuntimeException("Product not found"));

            // Retrieve inventory
            Inventory inventory =
                    inventoryRepository.findByProductIdAndStoreId(
                            productDTO.getId(),
                            placeOrderRequest.getStoreId()
                    );

            if (inventory == null) {
                throw new RuntimeException("Inventory not found");
            }

            // Update stock
            inventory.setStockLevel(
                    inventory.getStockLevel() - productDTO.getQuantity()
            );

            inventoryRepository.save(inventory);

            // Create order item
            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(orderDetails);
            orderItem.setProduct(product);
            orderItem.setQuantity(productDTO.getQuantity());

            orderItem.setPrice(
                    product.getPrice() * productDTO.getQuantity()
            );

            orderItemRepository.save(orderItem);
        }
    }
}