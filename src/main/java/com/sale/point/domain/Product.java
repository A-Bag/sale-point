package com.sale.point.domain;

import java.math.BigDecimal;

public class Product {
    private Long barCode;
    private String name;
    private BigDecimal price;

    public Product(Long barCode, String name, BigDecimal price) {
        this.barCode = barCode;
        this.name = name;
        this.price = price;
    }

    public Long getBarCode() {
        return barCode;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
