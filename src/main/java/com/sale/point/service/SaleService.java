package com.sale.point.service;

import com.sale.point.device.input.Scanner;
import com.sale.point.device.output.Printer;
import com.sale.point.device.output.Screen;
import com.sale.point.domain.Product;
import com.sale.point.exception.InvalidBarCodeException;
import com.sale.point.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class SaleService {
    private DbService dbService = new DbService();
    private Scanner scanner = new Scanner();
    private Screen screen = new Screen();
    private Printer printer = new Printer();

    public void process() {
        String barCode = "";
        Product product;
        List<Product> products = new ArrayList<>();
        BigDecimal totalPrice;

        while (!barCode.equals("exit")) {
            try {
                barCode = scanner.scanCode();
                if (barCode.equals("exit")) { continue; }
                product = dbService.fetchProduct(barCode);
            } catch (InvalidBarCodeException e) {
                screen.displayWarning("Invalid bar-code");
                continue;
            } catch (ProductNotFoundException e) {
                screen.displayWarning("Product not found");
                continue;
            }
            screen.displayProductNameAndPrice(product);
            products.add(product);
        }

        totalPrice = calculateTotalPrice(products);
        screen.displayTotalPrice(totalPrice);
        printer.printReceipt(products, totalPrice);
    }

    private BigDecimal calculateTotalPrice(List<Product> products) {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
