package com.mycompany.app.service.product.domain.repository;

import com.mycompany.app.service.product.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findAllByProductName(String productName);
}
