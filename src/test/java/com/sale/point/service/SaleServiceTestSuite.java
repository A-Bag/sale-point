package com.sale.point.service;

import com.sale.point.device.input.Scanner;
import com.sale.point.device.output.Printer;
import com.sale.point.device.output.Screen;
import com.sale.point.domain.Product;
import com.sale.point.exception.InvalidBarCodeException;
import com.sale.point.exception.ProductNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaleServiceTestSuite {

    @InjectMocks
    private SaleService saleService;
    @Mock
    private DbService dbServiceMock;
    @Mock
    private Scanner scannerMock;
    @Mock
    private Screen screenMock;
    @Mock
    private Printer printerMock;

    @Test
    public void testProcess() throws Exception {
        //Given
        when(scannerMock.scanCode())
                .thenReturn("1234561234567")
                .thenReturn("1234561234568")
                .thenThrow(new InvalidBarCodeException())
                .thenReturn("1234561234569")
                .thenReturn("exit");
        Product product1 = new Product("1234561234567", "product1", new BigDecimal(9.99));
        Product product2 = new Product("1234561234568", "product2", new BigDecimal(19.99));
        when(dbServiceMock.fetchProduct("1234561234567"))
                .thenReturn(product1);
        when(dbServiceMock.fetchProduct("1234561234568"))
                .thenReturn(product2);
        when(dbServiceMock.fetchProduct("1234561234569"))
                .thenThrow(new ProductNotFoundException());
        //When
        saleService.process();
        //Then
        verify(screenMock, times(1)).displayWarning("Invalid bar-code");
        verify(screenMock, times(1)).displayWarning("Product not found");
        verify(screenMock, times(1)).displayProductNameAndPrice(product1);
        verify(screenMock, times(1)).displayProductNameAndPrice(product2);
        verify(screenMock, times(1))
                .displayTotalPrice(new BigDecimal(29.98).setScale(2, RoundingMode.HALF_UP));
        verify(printerMock, times(1))
                .print(Arrays.asList(product1, product2), new BigDecimal(29.98).setScale(2, RoundingMode.HALF_UP));
    }
}
