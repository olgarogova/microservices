package com.mycompany.app.service.product.impl.openfeign;

import com.mycompany.app.service.product.domain.dto.SupplierDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "supplier-service", url = "http://localhost:8830/suppliers")
public interface SupplierServiceClient {

    @GetMapping("/{supplierId}")
    SupplierDto getSupplierById(@PathVariable("supplierId") int supplierId);
}
