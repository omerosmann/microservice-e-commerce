package com.kodlamaio.commonpackage.events.invoice;

import com.kodlamaio.commonpackage.events.Event;
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
public class InvoiceCreatedEvent implements Event {
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