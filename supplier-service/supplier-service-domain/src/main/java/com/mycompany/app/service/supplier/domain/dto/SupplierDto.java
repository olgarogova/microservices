package com.mycompany.app.service.supplier.domain.dto;

public class SupplierDto {
    private int supplierId;
    private String supplierName;
    private String phone;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int customerId) {
        this.supplierId = customerId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String customerName) {
        this.supplierName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
