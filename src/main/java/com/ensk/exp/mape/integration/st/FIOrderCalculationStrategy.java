package com.ensk.exp.mape.integration.st;

import com.ensk.exp.mape.exception.ServiceIntegrationExeption;
import com.ensk.exp.mape.service.OrderCalculationStrategy;

public class FIOrderCalculationStrategy implements OrderCalculationStrategy {

    @Override
    public void checkTradeDirection(String tradeDirection) throws ServiceIntegrationExeption {
        if (!("BUY".equals(tradeDirection) || "SELL".equals(tradeDirection))) {
            throw new ServiceIntegrationExeption(String.format("Unsupported trade direction:%s", tradeDirection));
        }
    }

    @Override
    public void checkQuantity(String tradeDirection, double tradeQuantity) throws ServiceIntegrationExeption {
        checkTradeDirection(tradeDirection);
        if ("BUY".equals(tradeDirection) || "SELL".equals(tradeDirection)) {
            if (tradeQuantity < 5 || tradeQuantity > 500) {
                throw new ServiceIntegrationExeption(String.format("Illegal EQ buy quantity:%f", tradeQuantity));
            }
        }
    }

    @Override
    public double getOrderCommission(String tradeDirection) throws ServiceIntegrationExeption {
        if ("BUY".equals(tradeDirection) || "SELL".equals(tradeDirection)) {
            return 0.001;
        } else {
            throw new ServiceIntegrationExeption(String.format("Unsupported FI trade direction commission:%s", tradeDirection));
        }
    }

}
