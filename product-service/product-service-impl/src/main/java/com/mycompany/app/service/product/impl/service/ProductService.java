package com.mycompany.app.service.product.impl.service;

import com.mycompany.app.service.product.domain.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getAllProducts(String productName);
    ProductEntity getProductById(int productId);
    ProductEntity createProduct(ProductEntity productEntity, int supplierId);
    ProductEntity updateProduct(int productId, ProductEntity updatedProductEntity);
    void deleteProduct(int productId);
}
