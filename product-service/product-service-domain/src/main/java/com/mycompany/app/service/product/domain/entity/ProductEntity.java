package com.mycompany.app.service.product.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", updatable = false, nullable = false)
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "supplier_id")
    private int supplierId;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "is_discontinued", columnDefinition="boolean default true")
    private boolean isDiscontinued;

    public ProductEntity() {
    }

    public ProductEntity(String productName, int supplierId, BigDecimal unitPrice, boolean isDiscontinued) {
        this.productName = productName;
        this.supplierId = supplierId;
        this.unitPrice = unitPrice;
        this.isDiscontinued = isDiscontinued;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isDiscontinued() {
        return isDiscontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        isDiscontinued = discontinued;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "Product {" +
                "productId = " + productId +
                ", productName = '" + productName + '\'' +
                ", supplierId = '" + supplierId +
                ", unitPrice = " + unitPrice +
                ", isDiscontinued = " + isDiscontinued +
                '}';
    }
}
