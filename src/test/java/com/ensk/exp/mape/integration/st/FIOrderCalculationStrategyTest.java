package com.ensk.exp.mape.integration.st;

import com.ensk.exp.mape.exception.ServiceIntegrationExeption;
import com.ensk.exp.mape.service.OrderCalculationStrategy;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FIOrderCalculationStrategyTest {

    // initial letter logogram of methods
    enum Type {
        CTD, CTDE, CQ, CQE, GOC, GOCE
    }

    private Type type;
    String tradeDirection;
    private Double tradeQuantity;
    private OrderCalculationStrategy fiOrderCalculationStrategy;

    public FIOrderCalculationStrategyTest(Type type, String tradeDirection, Double tradeQuantity) {
        this.type = type;
        this.tradeDirection = tradeDirection;
        this.tradeQuantity = tradeQuantity;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                        {Type.CTD, "BUY", 0.0},
                        {Type.CTD, "SELL", 0.0},
                        {Type.CTDE, "BUY123", 0.0},
                        {Type.CTDE, "9SELL9", 0.0},
                        {Type.CTDE, "", 0.0},
                        {Type.CTDE, null, 0.0},
                        {Type.CQ, "BUY", 5.0},
                        {Type.CQ, "BUY", 500.0},
                        {Type.CQ, "BUY", 290.0},
                        {Type.CQ, "BUY", 463.0},
                        {Type.CQ, "BUY", 162.0},
                        {Type.CQ, "SELL", 5.0},
                        {Type.CQ, "SELL", 500.0},
                        {Type.CQ, "SELL", 290.0},
                        {Type.CQ, "SELL", 463.0},
                        {Type.CQ, "SELL", 162.0},
                        {Type.CQE, "BUY", 4.99},
                        {Type.CQE, "BUY", 500.01},
                        {Type.CQE, "BUY", 747.29},
                        {Type.CQE, "BUY", -457.21},
                        {Type.CQE, "SELL", 4.99},
                        {Type.CQE, "SELL", 500.01},
                        {Type.CQE, "SELL", 747.29},
                        {Type.CQE, "SELL", -457.21},
                        {Type.GOC, "BUY", 0.0},
                        {Type.GOC, "SELL", 0.0},
                        {Type.GOCE, "UYLL-5", 0.0}});
    }

    @Before
    public void setUp() {
        fiOrderCalculationStrategy = new FIOrderCalculationStrategy();
    }

    @After
    public void tearDown() {}

    @Test
    public void testCheckTradeDirection() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.CTD);
        fiOrderCalculationStrategy.checkTradeDirection(tradeDirection);
    }

    @Test(expected = ServiceIntegrationExeption.class)
    public void testCheckTradeDirectionException() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.CTDE);
        fiOrderCalculationStrategy.checkTradeDirection(tradeDirection);
    }

    @Test
    public void testCheckQuantity() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.CQ);
        fiOrderCalculationStrategy.checkQuantity(tradeDirection, tradeQuantity);
    }

    @Test(expected = ServiceIntegrationExeption.class)
    public void testCheckQuantityException() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.CQE);
        fiOrderCalculationStrategy.checkQuantity(tradeDirection, tradeQuantity);
    }

    @Test
    public void testGetOrderCommission() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.GOC);
        fiOrderCalculationStrategy.getOrderCommission(tradeDirection);
    }

    @Test(expected = ServiceIntegrationExeption.class)
    public void testGetOrderCommissionException() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.GOCE);
        fiOrderCalculationStrategy.getOrderCommission(tradeDirection);
    }

}
