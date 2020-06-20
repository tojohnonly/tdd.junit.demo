package com.ensk.exp.mape.integration.st;

import com.ensk.exp.mape.exception.ServiceIntegrationExeption;
import com.ensk.exp.mape.service.OrderCalculationStrategy;

public class EQOrderCalculationStrategy implements OrderCalculationStrategy {

    @Override
    public void checkTradeDirection(String tradeDirection) throws ServiceIntegrationExeption {
        if (!("BUY".equals(tradeDirection) || "SELL".equals(tradeDirection))) {
            throw new ServiceIntegrationExeption(String.format("Unsupported trade direction:%s", tradeDirection));
        }
    }

    @Override
    public void checkQuantity(String tradeDirection, double tradeQuantity) throws ServiceIntegrationExeption {
        checkTradeDirection(tradeDirection);
        if ("BUY".equals(tradeDirection)) {
            if (tradeQuantity < 1.0 || tradeQuantity > 1000.0) {
                throw new ServiceIntegrationExeption(String.format("Illegal EQ buy quantity:%f", tradeQuantity));
            }
        } else if ("SELL".equals(tradeDirection)) {
            if (tradeQuantity < 0.0) {
                throw new ServiceIntegrationExeption(String.format("Illegal EQ sell quantity:%f", tradeQuantity));
            }
        }
    }

    @Override
    public double getOrderCommission(String tradeDirection) throws ServiceIntegrationExeption {
        checkTradeDirection(tradeDirection);
        if ("BUY".equals(tradeDirection)) {
            return 0.002;
        } else if ("SELL".equals(tradeDirection)) {
            return 0.0025;
        } else {
            throw new ServiceIntegrationExeption(String.format("Unsupported EQ trade direction commission:%s", tradeDirection));
        }
    }

}
