package com.mycompany.app.service.supplier.impl;

import com.mycompany.app.service.supplier.domain.entity.SupplierEntity;
import com.mycompany.app.service.supplier.impl.exceptions.ResourceNotFoundException;
import com.mycompany.app.service.supplier.impl.service.SupplierService;
import com.mycompany.app.service.supplier.domain.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierEntity> getAllSuppliers(String supplierName){
        List<SupplierEntity> supplierEntities = new ArrayList<>();
        if (supplierName == null){
            supplierEntities.addAll(supplierRepository.findAll());
        } else {
            supplierEntities.addAll(supplierRepository.findAllBySupplierName(supplierName));
        }
        if (supplierEntities.isEmpty()) {
            throw new ResourceNotFoundException("Supplier not found");
        }
        return supplierEntities;
    }

    @Override
    public SupplierEntity getSupplierById(int supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    @Override
    public SupplierEntity createSupplier(SupplierEntity supplierEntity) {
        return supplierRepository
                    .save(new SupplierEntity(supplierEntity.getSupplierName(), supplierEntity.getPhone()));
    }

    @Override
    public SupplierEntity updateSupplier(int supplierId, SupplierEntity updatedSupplierEntity) {
        SupplierEntity supplierData = supplierRepository.findById(supplierId).orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
        supplierData.setSupplierName(updatedSupplierEntity.getSupplierName());
        supplierData.setPhone(updatedSupplierEntity.getPhone());
        supplierRepository.save(supplierData);
        return supplierData;
    }

    @Override
    public void addProductsFromSupplier(int supplierId) {
        StubProductService.addProducts();
    }

    @Override
    public void deleteSupplier(int supplierId) {
        try {
            supplierRepository.deleteById(supplierId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Supplier with id " + supplierId + " does not exist");
        }
    }
}
