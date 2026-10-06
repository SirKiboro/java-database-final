package com.project.code.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaceOrderRequestDTO {
    private Long storeId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String datetime;
    private List<PurchaseProductDTO> purchaseProduct;
    private Double totalPrice;


}
