package com.onlineshop.model;

import com.onlineshop.enums.ProductCategory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Product {
    private final long id;
    private String name;
    private BigDecimal price;
    private int quantityAvailable;
    private final ProductCategory category;

    protected Product(long id, String name, BigDecimal price, int quantityAvailable, ProductCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("Product category must not be null");
        }

        if (quantityAvailable < 0) {
            throw new IllegalArgumentException("Quantity available must not be negative");
        }

        this.id = id;
        this.name = requireNotBlank(name, "Product name");
        this.price = normalizePrice(price);
        this.quantityAvailable = quantityAvailable;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public ProductCategory getCategory() {
        return category;
    }

    /* Behavior */
    public void changePrice(BigDecimal newPrice) {
        this.price = normalizePrice(newPrice);
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity to increase must be greater than zero");
        }
        this.quantityAvailable = this.quantityAvailable + quantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity to decrease must be greater than zero");
        }
        if (quantity > quantityAvailable) {
            throw new IllegalArgumentException(
                    "Insufficient stock: requested " + quantity
                            + ", available " + quantityAvailable
            );
        }
        this.quantityAvailable = this.quantityAvailable - quantity;
    }

    public abstract boolean requiresShipping();

    private static BigDecimal normalizePrice(BigDecimal newPrice) {
        if (newPrice == null) {
            throw new IllegalArgumentException("Price must not be null");
        }

        if (newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must not be negative");
        }

        return newPrice.setScale(2, RoundingMode.HALF_UP);
    }

    private static String requireNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " must not be null or blank"
            );
        }
        return value.trim();
    }

}
