package com.kodlamaio.saleservice.repository;


import com.kodlamaio.saleservice.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {}