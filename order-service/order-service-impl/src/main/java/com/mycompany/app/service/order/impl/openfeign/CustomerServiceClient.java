package com.mycompany.app.service.order.impl.openfeign;

import com.mycompany.app.service.order.domain.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8820/customers")
public interface CustomerServiceClient {

    @GetMapping("/{customerId}")
    CustomerDto getCustomerById(@PathVariable("customerId") int customerId);
}
