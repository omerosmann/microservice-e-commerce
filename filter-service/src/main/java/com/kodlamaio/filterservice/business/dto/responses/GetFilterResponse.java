package com.kodlamaio.filterservice.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetFilterResponse {
    private String id;
    private UUID categoryId;
    private UUID productId;
    private String categoryName;
    private String productName;
    private int productQuantity;
    private double productPrice;
    private String productDescription;
    private String state;
}