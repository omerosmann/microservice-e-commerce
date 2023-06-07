package com.kodlamaio.saleservice.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSaleResponse {
    private UUID id;
    private UUID productId;
    private double price;
    private int quantity;
    private double totalPrice;
    private LocalDateTime saleTime;
}