package com.mycompany.app.service.customer.impl.controller;

import com.mycompany.app.service.customer.domain.dto.CustomerDto;
import com.mycompany.app.service.customer.domain.entity.CustomerEntity;
import com.mycompany.app.service.customer.impl.CustomerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final CustomerServiceImpl customerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CustomerRestController(CustomerServiceImpl customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDto> getAllCustomers(@RequestParam(required = false) String customerName){
        return customerService.getAllCustomers(customerName).stream().map(customerEntity -> modelMapper.map(customerEntity, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{customerId}")
    public CustomerDto getCustomerById(@PathVariable("customerId") int customerId) {
        CustomerEntity customerEntity = customerService.getCustomerById(customerId);
        return modelMapper.map(customerEntity, CustomerDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
        CustomerEntity customerEntityRequest = modelMapper.map(customerDto, CustomerEntity.class);
        CustomerEntity customerEntity = customerService.saveCustomer(customerEntityRequest);
        return modelMapper.map(customerEntity, CustomerDto.class);
    }

    @PutMapping("/{customerId}")
    public CustomerDto updateCustomer(@PathVariable("customerId") int customerId, @RequestBody CustomerDto updatedCustomerDto) {
        CustomerEntity customerEntityRequest = modelMapper.map(updatedCustomerDto, CustomerEntity.class);
        CustomerEntity customerEntity = customerService.updateCustomer(customerId, customerEntityRequest);
        return modelMapper.map(customerEntity, CustomerDto.class);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") int customerId) {
        customerService.deleteCustomer(customerId);
    }
}
