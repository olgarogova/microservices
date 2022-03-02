package com.mycompany.app.service.supplier.impl.service;

import com.mycompany.app.service.supplier.domain.entity.SupplierEntity;
import com.mycompany.app.service.supplier.domain.dto.ProductDto;

import java.util.List;

public interface SupplierService {
    List<SupplierEntity> getAllSuppliers(String customerName);
    SupplierEntity getSupplierById(int supplierId);
    SupplierEntity createSupplier(SupplierEntity supplierEntity);
    SupplierEntity updateSupplier(int supplierId, SupplierEntity updatedSupplierEntity);
    void addProductsFromSupplier(int supplierId, List<ProductDto> productDtos);
    void deleteSupplier(int supplierId);
}
