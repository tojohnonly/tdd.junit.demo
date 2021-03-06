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
public class EQOrderCalculationStrategyTest {

    // initial letter logogram of methods
    enum Type {
        CTD, CTDE, CQ, CQE, GOC, GOCE
    }

    private Type type;
    String tradeDirection;
    private Double tradeQuantity;
    private OrderCalculationStrategy eqOrderCalculationStrategy;

    public EQOrderCalculationStrategyTest(Type type, String tradeDirection, Double tradeQuantity) {
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
                        {Type.CQ, "BUY", 1.0},
                        {Type.CQ, "BUY", 1000.0},
                        {Type.CQ, "BUY", 175.0},
                        {Type.CQ, "BUY", 463.0},
                        {Type.CQ, "BUY", 892.0},
                        {Type.CQ, "SELL", 0.38},
                        {Type.CQ, "SELL", 167.43},
                        {Type.CQ, "SELL", 1997.0},
                        {Type.CQE, "BUY", 0.99},
                        {Type.CQE, "BUY", 1000.01},
                        {Type.CQE, "BUY", 1362.0},
                        {Type.CQE, "BUY", -782.0},
                        {Type.GOC, "BUY", 0.0},
                        {Type.GOC, "SELL", 0.0},
                        {Type.GOCE, "BUSE", 0.0}});
    }

    @Before
    public void setUp() {
        eqOrderCalculationStrategy = new EQOrderCalculationStrategy();
    }

    @After
    public void tearDown() {}

    @Test
    public void testCheckTradeDirection() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.CTD);
        eqOrderCalculationStrategy.checkTradeDirection(tradeDirection);
    }

    @Test(expected = ServiceIntegrationExeption.class)
    public void testCheckTradeDirectionException() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.CTDE);
        eqOrderCalculationStrategy.checkTradeDirection(tradeDirection);
    }

    @Test
    public void testCheckQuantity() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.CQ);
        eqOrderCalculationStrategy.checkQuantity(tradeDirection, tradeQuantity);
    }

    @Test(expected = ServiceIntegrationExeption.class)
    public void testCheckQuantityException() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.CQE);
        eqOrderCalculationStrategy.checkQuantity(tradeDirection, tradeQuantity);
    }

    @Test
    public void testGetOrderCommission() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.GOC);
        eqOrderCalculationStrategy.getOrderCommission(tradeDirection);
    }

    @Test(expected = ServiceIntegrationExeption.class)
    public void testGetOrderCommissionException() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.GOCE);
        eqOrderCalculationStrategy.getOrderCommission(tradeDirection);
    }

}
