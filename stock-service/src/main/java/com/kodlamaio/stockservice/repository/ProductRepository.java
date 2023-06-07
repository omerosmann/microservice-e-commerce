package com.kodlamaio.stockservice.repository;

import com.kodlamaio.stockservice.entities.Product;
import com.kodlamaio.stockservice.entities.enums.State;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByStateIsNot(State state);

    @Modifying
    @Transactional
    @Query(value = "update Product set state =:state where id =:id")
    void changeStateByProductId(State state, UUID id);
}