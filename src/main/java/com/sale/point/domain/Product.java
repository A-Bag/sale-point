package com.sale.point.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String barCode;
    private String name;
    private BigDecimal price;

    public Product(String barCode, String name, BigDecimal price) {
        this.barCode = barCode;
        this.name = name;
        this.price = price;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(barCode, product.barCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barCode);
    }
}
