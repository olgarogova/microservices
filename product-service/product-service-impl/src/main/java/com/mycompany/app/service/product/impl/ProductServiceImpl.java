package com.mycompany.app.service.product.impl;

import com.mycompany.app.service.product.domain.dto.SupplierDto;
import com.mycompany.app.service.product.domain.entity.ProductEntity;
import com.mycompany.app.service.product.impl.exceptions.ResourceNotFoundException;
import com.mycompany.app.service.product.domain.repository.ProductRepository;
import com.mycompany.app.service.product.impl.openfeign.SupplierServiceClient;
import com.mycompany.app.service.product.impl.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SupplierServiceClient supplierServiceClient;

    static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, SupplierServiceClient supplierServiceClient) {
        this.productRepository = productRepository;
        this.supplierServiceClient = supplierServiceClient;
    }

    @Override
    public List<ProductEntity> getAllProducts(String productName){
        List<ProductEntity> productEntities = new ArrayList<>();
        if (productName == null){
            productEntities.addAll(productRepository.findAll());
        } else {
            productEntities.addAll(productRepository.findAllByProductName(productName));
        }
        if (productEntities.isEmpty()) {
            throw new ResourceNotFoundException("Product not found");
        }
        return productEntities;
    }

    @Override
    public ProductEntity getProductById(int productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public ProductEntity createProduct(ProductEntity productEntity, int supplierId) {
        supplierServiceClient.getSupplierById(supplierId);
        return productRepository
                    .save(new ProductEntity(productEntity.getProductName(), productEntity.getSupplierId(), productEntity.getUnitPrice(), productEntity.isDiscontinued()));
    }


    @Override
    public ProductEntity updateProduct(int productId, ProductEntity updatedProductEntity) {
        ProductEntity productData = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productData.setProductName(updatedProductEntity.getProductName());
        productData.setSupplierId(updatedProductEntity.getSupplierId());
        productData.setUnitPrice(updatedProductEntity.getUnitPrice());
        productData.setDiscontinued(updatedProductEntity.isDiscontinued());
        productRepository.save(productData);
        return productData;
    }

    @Override
    public void deleteProduct(int productId) {
        try {
            productRepository.deleteById(productId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Product with id " + productId + " does not exist");
        }
    }
}
