package com.mycompany.app.service.product.impl;

import com.mycompany.app.service.product.domain.entity.ProductEntity;
import com.mycompany.app.service.product.impl.exceptions.ResourceNotFoundException;
import com.mycompany.app.service.product.domain.repository.ProductRepository;
import com.mycompany.app.service.product.impl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public ProductEntity createProduct(ProductEntity productEntity) {
        return productRepository
                    .save(new ProductEntity(productEntity.getProductName(), productEntity.getUnitPrice(), productEntity.isDiscontinued()));
    }

    @Override
    public ProductEntity updateProduct(int productId, ProductEntity updatedProductEntity) {
        ProductEntity productData = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productData.setProductName(updatedProductEntity.getProductName());
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
