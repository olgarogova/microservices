package com.mycompany.app.service.supplier.domain.repository;

import com.mycompany.app.service.supplier.domain.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Integer> {
    List<SupplierEntity> findAllBySupplierName(String supplierName);
}
