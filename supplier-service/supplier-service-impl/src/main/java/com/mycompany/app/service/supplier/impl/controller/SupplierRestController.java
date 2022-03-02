package com.mycompany.app.service.supplier.impl.controller;

import com.mycompany.app.service.supplier.domain.dto.SupplierDto;
import com.mycompany.app.service.supplier.domain.entity.SupplierEntity;
import com.mycompany.app.service.supplier.impl.SupplierServiceImpl;
import com.mycompany.app.service.supplier.domain.dto.ProductDto;
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
@RequestMapping("/suppliers")
public class SupplierRestController {

    @Autowired
    private ModelMapper modelMapper;

    private final SupplierServiceImpl supplierService;

    @Autowired
    public SupplierRestController(SupplierServiceImpl supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<SupplierDto> getAllSuppliers(@RequestParam(required = false) String supplierName){
        return supplierService.getAllSuppliers(supplierName).stream().map(supplierEntity -> modelMapper.map(supplierEntity, SupplierDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{supplierId}")
    public SupplierDto getSupplierById(@PathVariable("supplierId") int supplierId) {
        SupplierEntity supplierEntity = supplierService.getSupplierById(supplierId);
        return modelMapper.map(supplierEntity, SupplierDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SupplierDto createSupplier(@RequestBody SupplierDto supplierDto) {
        SupplierEntity supplierEntityRequest = modelMapper.map(supplierDto, SupplierEntity.class);
        SupplierEntity supplierEntity = supplierService.createSupplier(supplierEntityRequest);
        return modelMapper.map(supplierEntity, SupplierDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add-products/{supplierId}")
    public void addProductsFromSupplier(@PathVariable("supplierId") int supplierId, @RequestBody List<ProductDto> productDtos){
        supplierService.addProductsFromSupplier(supplierId, productDtos);
    }

    @PutMapping("/{supplierId}")
    public SupplierDto updateSupplier(@PathVariable("supplierId") int supplierId, @RequestBody SupplierDto updatedSupplierDto) {
        SupplierEntity supplierEntityRequest = modelMapper.map(updatedSupplierDto, SupplierEntity.class);
        SupplierEntity supplierEntity = supplierService.updateSupplier(supplierId, supplierEntityRequest);
        return modelMapper.map(supplierEntity, SupplierDto.class);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{supplierId}")
    public void deleteSupplier(@PathVariable("supplierId") int supplierId) {
        supplierService.deleteSupplier(supplierId);
    }
}
