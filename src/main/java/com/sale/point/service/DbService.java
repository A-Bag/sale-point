package com.sale.point.service;

import com.sale.point.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class DbService {
    List<Product> fetchProducts(List<Long> barCodes){
        return new ArrayList<>();
    }

    Product fetchProduct(String barCode) {
        return null;
    }
}
