package com.mycompany.app.service.order.impl;

import com.mycompany.app.service.order.domain.entity.OrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StubProductService {

    static final Logger logger = LoggerFactory.getLogger(StubProductService.class);

    public void addProductToOrder (OrderEntity order){
        logger.info("Add to order " + order + " products from Products");
    }

    public void deleteProductFromOrder (OrderEntity order){
        logger.info("Delete from order " + order + " products from Products");
    }
}
