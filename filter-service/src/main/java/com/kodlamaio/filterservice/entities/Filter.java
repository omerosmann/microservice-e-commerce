package com.kodlamaio.filterservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Filter {
    @Id
    private String  id;

    private UUID categoryId;
    private UUID productId;
    private String categoryName;
    private String productName;
    private int productQuantity;
    private double productPrice;
    private String productDescription;
    private String state;
}