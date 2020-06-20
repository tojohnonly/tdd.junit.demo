package com.ensk.exp.mape.integration.tr;

import com.ensk.exp.mape.exception.ServiceIntegrationExeption;
import com.ensk.exp.mape.service.OrderPriceCalculationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assume;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CommonOrderPriceCalculationServiceTest {

    // initial letter logogram of methods
    enum Type {
        GOP, GOPE
    }

    private Type type;
    String instrumentName;
    String instrumentType;
    String tradeDirection;
    double tradeQuantity;
    double orderPrice;

    private OrderPriceCalculationService orderPriceCalculationService;

    public CommonOrderPriceCalculationServiceTest(Type type, String instrumentName, String instrumentType,
        String tradeDirection, double tradeQuantity, double orderPrice) {
        this.type = type;
        this.instrumentName = instrumentName;
        this.instrumentType = instrumentType;
        this.tradeDirection = tradeDirection;
        this.tradeQuantity = tradeQuantity;
        this.orderPrice = orderPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                        {Type.GOP, "ABMX", "EQ", "BUY", 15, 3688.36},
                        {Type.GOP, "ABMX", "EQ", "SELL", 639, 157202.63},
                        {Type.GOP, "DFOP", "EQ", "BUY", 715, 30448.28},
                        {Type.GOP, "DFOP", "EQ", "SELL", 12.5, 532.58},
                        {Type.GOP, "BOGO", "FI", "BUY", 30, 20720.70},
                        {Type.GOP, "BOGO", "FI", "SELL", 268.37, 185360.48},
                        {Type.GOPE, "ABMX", "EQ", "BUY", 1001, 26189.73},
                        {Type.GOPE, "", "EQ", "BUY", 1001, 26189.73},
                        {Type.GOPE, "ABMX", "EQ", "SELL", -1, -263.75},
                        {Type.GOPE, "ABMX", "EQ", null, 639, 157202.63},
                        {Type.GOPE, "DFOP1", " EQ", "BUY", 715, 30448.28},
                        {Type.GOPE, "DFOP", "EQ2", "SELL", 12.5, 532.58},
                        {Type.GOPE, null, "EQ", "SELL", 12.5, 532.58},
                        {Type.GOPE, "DFOP", "", "SELL", 12.5, 532.58},
                        {Type.GOPE, "BOGO", "FI", "BUY3", 30, 20720.70},
                        {Type.GOPE, "BOGO", "FI", "SELL", 4.99, 2047.33},
                        {Type.GOPE, "BOGO", null, "SELL", 268.37, 185360.48},
                        {Type.GOPE, "BOGO", "FI", "", 268.37, 185360.48}
                });
    }

    @Before
    public void setUp() {
        orderPriceCalculationService = new CommonOrderPriceCalculationService();
    }

    @After
    public void tearDown() {}

    @Test
    public void testGetOrderPrice() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.GOP);
        Assert.assertEquals(orderPrice,
            orderPriceCalculationService.getOrderPrice(instrumentName, instrumentType, tradeDirection, tradeQuantity), 0.0);
    }

    @Test(expected = ServiceIntegrationExeption.class)
    public void testGetOrderPriceException() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.GOPE);
        Assert.assertEquals(orderPrice,
            orderPriceCalculationService.getOrderPrice(instrumentName, instrumentType, tradeDirection, tradeQuantity), 0.0);
    }
}
