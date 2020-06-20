package com.ensk.exp.mape.service;

import com.ensk.exp.mape.exception.ServiceIntegrationExeption;

public interface OrderCalculationStrategy {

    void checkTradeDirection(String tradeDirection) throws ServiceIntegrationExeption;

    void checkQuantity(String tradeDirection, double tradeQuantity) throws ServiceIntegrationExeption;

    double getOrderCommission(String tradeDirection) throws ServiceIntegrationExeption;

}
