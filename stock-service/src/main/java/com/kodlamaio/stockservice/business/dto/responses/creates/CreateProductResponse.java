package com.kodlamaio.stockservice.business.dto.responses.creates;

import com.kodlamaio.stockservice.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductResponse {
    private UUID id;
    private UUID categoryId;
    private String categoryName;
    private String name;
    private State state;
    private int quantity;
    private double price;
    private String description;
}
