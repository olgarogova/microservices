package com.mycompany.app.service.supplier.impl.openfeign;

import com.mycompany.app.service.supplier.domain.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", url = "http://localhost:8810/products")
public interface ProductServiceClient {

    @PostMapping
    ProductDto createProduct(@RequestBody ProductDto productDto);
}