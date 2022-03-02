package com.mycompany.app.service.product.impl.controller;

import com.mycompany.app.service.product.domain.entity.ProductEntity;
import com.mycompany.app.service.product.impl.ProductServiceImpl;
import com.mycompany.app.service.product.domain.dto.ProductDto;
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
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    private ModelMapper modelMapper;

    private final ProductServiceImpl productService;

    @Autowired
    public ProductRestController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts(@RequestParam(required = false) String productName){
        return productService.getAllProducts(productName).stream().map(productEntity -> modelMapper.map(productEntity, ProductDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable("productId") int productId) {
        ProductEntity productEntity = productService.getProductById(productId);
        return modelMapper.map(productEntity, ProductDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        ProductEntity productEntityRequest = modelMapper.map(productDto, ProductEntity.class);
        ProductEntity productEntity = productService.createProduct(productEntityRequest, productDto.getSupplierId());
        return modelMapper.map(productEntity, ProductDto.class);
    }

    @PutMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable("productId") int productId, @RequestBody ProductDto updatedProductDto) {
        ProductEntity productEntityRequest = modelMapper.map(updatedProductDto, ProductEntity.class);
        ProductEntity productEntity = productService.updateProduct(productId, productEntityRequest);
        return modelMapper.map(productEntity, ProductDto.class);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") int productId) {
        productService.deleteProduct(productId);
    }
}
