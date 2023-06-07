package com.kodlamaio.invoiceservice.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetInvoiceResponse {
    private String id;
    private String categoryName;
    private String productName;
    private String productDescription;
    private int quantity;
    private double price;
    private double totalPrice;
    private LocalDateTime saleTime;
}