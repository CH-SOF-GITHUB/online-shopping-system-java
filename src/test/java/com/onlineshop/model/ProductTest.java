package com.onlineshop.model;

import com.onlineshop.enums.ProductCategory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private Product createLaptop() {
        return new PhysicalProduct(
                1L,
                "Laptop",
                new BigDecimal("999.99"),
                10,
                ProductCategory.LAPTOPS
        );
    }

    @Test
    void shouldRoundPriceHalfUp() {
        PhysicalProduct product = new PhysicalProduct(
                1L,
                "Phone",
                new BigDecimal("19.995"),
                10,
                ProductCategory.SMARTPHONES);
        assertEquals(0, new BigDecimal("20.00").compareTo(product.getPrice()));
    }

    @Test
    void shouldRoundPriceDownWhenThirdDecimalIsLessThanFive() {
        PhysicalProduct product = new PhysicalProduct(
                2L,
                "Headphones",
                new BigDecimal("19.994"),
                10,
                ProductCategory.HEADPHONES);

        assertEquals(0, new BigDecimal("19.99").compareTo(product.getPrice()));
    }

    @Test
    void shouldRejectNegativePrice() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PhysicalProduct(
                        3L,
                        "Laptop",
                        new BigDecimal("-10.00"),
                        5,
                        ProductCategory.LAPTOPS));
    }

    @Test
    void shouldRejectNullPrice() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PhysicalProduct(
                        4L,
                        "Laptop",
                        null,
                        5,
                        ProductCategory.LAPTOPS));
    }

    @Test
    void shouldRejectNullName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PhysicalProduct(
                        5L,
                        null,
                        new BigDecimal("10.00"),
                        5,
                        ProductCategory.ACCESSORIES));
    }


    @Test
    void shouldRejectEmptyName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PhysicalProduct(
                        6L,
                        "",
                        new BigDecimal("10.00"),
                        5,
                        ProductCategory.ACCESSORIES));
    }


    @Test
    void shouldRejectBlankName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PhysicalProduct(
                        7L,
                        "   ",
                        new BigDecimal("10.00"),
                        5,
                        ProductCategory.ACCESSORIES));
    }


    @Test
    void shouldRejectNullCategory() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PhysicalProduct(
                        8L,
                        "Laptop",
                        new BigDecimal("10.00"),
                        5,
                        null));
    }

    @Test
    void shouldRejectNegativeQuantityAvailable() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PhysicalProduct(
                        9L,
                        "Laptop",
                        new BigDecimal("10.00"),
                        -1,
                        ProductCategory.LAPTOPS));
    }


    @Test
    void shouldIncreaseStock() {
        PhysicalProduct product = new PhysicalProduct(
                10L,
                "Laptop",
                new BigDecimal("999.99"),
                5,
                ProductCategory.LAPTOPS);

        product.increaseStock(3);

        assertEquals(8, product.getQuantityAvailable());
    }

    @Test
    void shouldRejectZeroQuantityWhenIncreasingStock() {
        PhysicalProduct product = new PhysicalProduct(
                11L,
                "Laptop",
                new BigDecimal("999.99"),
                5,
                ProductCategory.LAPTOPS);

        assertThrows(
                IllegalArgumentException.class,
                () -> product.increaseStock(0)
        );
    }

    @Test
    void shouldRejectNegativeQuantityWhenIncreasingStock() {
        PhysicalProduct product = new PhysicalProduct(
                12L,
                "Laptop",
                new BigDecimal("999.99"),
                5,
                ProductCategory.LAPTOPS
        );

        assertThrows(IllegalArgumentException.class, () -> product.increaseStock(-2));
    }


    @Test
    void shouldDecreaseStock() {
        Product product = createLaptop();
        product.decreaseStock(2);

        assertEquals(8, product.getQuantityAvailable());
    }

    @Test
    void shouldDecreaseStockOnDigitalProduct() {
        DigitalProduct product = new DigitalProduct(
                2L,
                "E-book",
                new BigDecimal("19.99"),
                10,
                ProductCategory.ACCESSORIES
        );
        product.decreaseStock(3);
        assertEquals(7, product.getQuantityAvailable());
        assertFalse(product.requiresShipping());
    }

    @Test
    void shouldRejectZeroQuantityWhenDecreasingStock() {
        PhysicalProduct product = new PhysicalProduct(
                14L,
                "Laptop",
                new BigDecimal("999.99"),
                5,
                ProductCategory.LAPTOPS);

        assertThrows(IllegalArgumentException.class, () -> product.decreaseStock(0));
    }


    @Test
    void shouldRejectNegativeQuantityWhenDecreasingStock() {
        PhysicalProduct product = new PhysicalProduct(
                15L,
                "Laptop",
                new BigDecimal("999.99"),
                5,
                ProductCategory.LAPTOPS);

        assertThrows(IllegalArgumentException.class, () -> product.decreaseStock(-2));
    }


    @Test
    void shouldRejectDecreasingStockBelowZero() {
        PhysicalProduct product = new PhysicalProduct(
                16L,
                "Laptop",
                new BigDecimal("999.99"),
                5,
                ProductCategory.LAPTOPS);

        assertThrows(IllegalArgumentException.class, () -> product.decreaseStock(7));

        assertEquals(5, product.getQuantityAvailable());
    }


    @Test
    void shouldAllowDecreasingStockToZero() {
        PhysicalProduct product = new PhysicalProduct(
                17L,
                "Laptop",
                new BigDecimal("999.99"),
                5,
                ProductCategory.LAPTOPS);

        product.decreaseStock(5);

        assertEquals(0, product.getQuantityAvailable());
    }


    @Test
    void shouldChangePrice() {
        PhysicalProduct product = new PhysicalProduct(
                18L,
                "Laptop",
                new BigDecimal("999.99"),
                5,
                ProductCategory.LAPTOPS);

        product.changePrice(new BigDecimal("1199.99"));

        assertEquals(0, new BigDecimal("1199.99").compareTo(product.getPrice()));
    }


    @Test
    void shouldRejectNegativePriceWhenChangingPrice() {
        PhysicalProduct product = new PhysicalProduct(
                19L,
                "Laptop",
                new BigDecimal("999.99"),
                5,
                ProductCategory.LAPTOPS);

        assertThrows(IllegalArgumentException.class, () -> product.changePrice(new BigDecimal("-10.00")));

        assertEquals(0, new BigDecimal("999.99").compareTo(product.getPrice()));
    }


    @Test
    void shouldRejectNullPriceWhenChangingPrice() {
        PhysicalProduct product = new PhysicalProduct(
                20L,
                "Laptop",
                new BigDecimal("999.99"),
                5,
                ProductCategory.LAPTOPS
        );

        assertThrows(IllegalArgumentException.class, () -> product.changePrice(null));

        assertEquals(0, new BigDecimal("999.99").compareTo(product.getPrice()));
    }


    @Test
    void shouldRoundPriceWhenChangingPrice() {
        PhysicalProduct product = new PhysicalProduct(
                21L,
                "Laptop",
                new BigDecimal("999.99"),
                5,
                ProductCategory.LAPTOPS);

        product.changePrice(new BigDecimal("1200.995"));

        assertEquals(0, new BigDecimal("1201.00").compareTo(product.getPrice()));
    }


    @Test
    void shouldRequireShippingForPhysicalProduct() {
        PhysicalProduct product = new PhysicalProduct(
                22L,
                "Laptop",
                new BigDecimal("999.99"),
                5,
                ProductCategory.LAPTOPS);

        assertTrue(product.requiresShipping());
    }


    @Test
    void shouldNotRequireShippingForDigitalProduct() {
        DigitalProduct product = new DigitalProduct(
                23L,
                "E-book",
                new BigDecimal("19.99"),
                10,
                ProductCategory.ACCESSORIES);

        assertFalse(product.requiresShipping());
    }

    @Test
    void shouldTrimProductName() {
        PhysicalProduct product = new PhysicalProduct(
                1L, "  Phone  ", new BigDecimal("19.99"), 10, ProductCategory.SMARTPHONES);

        assertEquals("Phone", product.getName());
    }

    @Test
    void shouldSupportPolymorphismForShippingRequirements() {
        List<Product> products = List.of(
                new PhysicalProduct(
                        1L,
                        "Laptop",
                        new BigDecimal("1200.00"),
                        10,
                        ProductCategory.LAPTOPS
                ),
                new DigitalProduct(
                        2L,
                        "E-book",
                        new BigDecimal("19.99"),
                        100,
                        ProductCategory.ACCESSORIES
                )
        );

        List<Boolean> results = products.stream()
                .map(Product::requiresShipping)
                .toList();

        assertEquals(List.of(true, false), results);
    }
}
