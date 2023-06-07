package com.kodlamaio.stockservice.business.dto.requests.updates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    private UUID categoryId;
    private String categoryName;
    private String name;
    private int quantity;
    private double price;

    @Length(min = 10, max = 50, message = "The description must contain at least 10 characters and at most 50 characters.")
    private String description;
}
