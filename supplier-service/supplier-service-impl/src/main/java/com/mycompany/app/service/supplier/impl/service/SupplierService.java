package com.mycompany.app.service.supplier.impl.service;

import com.mycompany.app.service.supplier.domain.entity.SupplierEntity;

import java.util.List;

public interface SupplierService {
    List<SupplierEntity> getAllSuppliers(String customerName);
    SupplierEntity getSupplierById(int supplierId);
    SupplierEntity createSupplier(SupplierEntity supplierEntity);
    SupplierEntity updateSupplier(int supplierId, SupplierEntity updatedSupplierEntity);
    void deleteSupplier(int supplierId);
}
