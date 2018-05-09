package com.sale.point.service;

import com.sale.point.device.input.Scanner;
import com.sale.point.device.output.Printer;
import com.sale.point.device.output.Screen;
import com.sale.point.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleService {
    private DbService dbService = new DbService();
    private Scanner scanner = new Scanner();
    private Screen screen = new Screen();
    private Printer printer = new Printer();

    public void process() {
        String barCode = "";
        List<Product> products = new ArrayList<>();
        BigDecimal totalPrice;

        while(!barCode.equals("exit")) {
            barCode = scanner.scanCode();
            if (barCode.isEmpty()) {
                screen.displayWarning("Invalid bar-code");
            }
            Product product = dbService.fetchProduct(barCode);
            if (product == null) {
                screen.displayWarning("Product not found");
            }
            screen.displayProductNameAndPrice(product);
            products.add(product);
        }

        totalPrice = calculateTotalPrice(products);
        screen.displayTotalPrice(totalPrice);
        printer.print(products, totalPrice);
    }

    private BigDecimal calculateTotalPrice(List<Product> products) {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current));
    }
}
