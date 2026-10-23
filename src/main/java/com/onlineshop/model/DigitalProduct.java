package com.onlineshop.model;

import com.onlineshop.enums.ProductCategory;

import java.math.BigDecimal;

public class DigitalProduct extends Product {
    // constructor

    public DigitalProduct(long id, String name, BigDecimal price, int quantityAvailable, ProductCategory category) {
        super(id, name, price, quantityAvailable, category);
    }

    @Override
    public boolean requiresShipping() {
        return false;
    }
}
