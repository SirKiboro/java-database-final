package com.project.code.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseProductDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private Double total;


}
