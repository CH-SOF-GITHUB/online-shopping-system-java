package com.onlineshop.model;

import com.onlineshop.enums.ProductCategory;

import java.math.BigDecimal;

public class PhysicalProduct extends Product{

    // constructor

    public PhysicalProduct(long id, String name, BigDecimal price, int quantityAvailable, ProductCategory category) {
        super(id, name, price, quantityAvailable, category);
    }

    // Dans PhysicalProduct, la méthode requiresShipping() doit retourner true, car un produit physique nécessite
    // une livraison.

    @Override
    public boolean requiresShipping() {
        return true;
    }
}
