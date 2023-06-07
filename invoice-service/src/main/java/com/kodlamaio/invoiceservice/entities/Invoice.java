package com.kodlamaio.invoiceservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    private String id;

    private UUID productId;
    private String categoryName;
    private String productName;
    private String productDescription;
    private int quantity;
    private double price;
    private double totalPrice;
    private String cardHolder;
    private LocalDateTime saleTime;
}
