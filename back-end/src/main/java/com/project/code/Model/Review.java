package com.project.code.Model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
@AllArgsConstructor
public class Review {

    @Id
    private String id;

    @NotNull(message = "Customer-id cannot be empty")
    private Long customerId;

    @NotNull(message = "Product-id cannot be empty")
    private Long productId;

    @NotNull(message = "Store-id cannot be empty")
    private Long storeId;


    @NotNull(message = "rating cannot be empty")
    @Min(1)
    @Max(5)
    private Integer rating;

    private String comment;

}
