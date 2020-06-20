package com.ensk.exp.mape.service;

import com.ensk.exp.mape.exception.ServiceIntegrationExeption;

public interface OrderPriceCalculationService {

	double getOrderPrice(String instrumentName, String instrumentType, String tradeDirection, double tradeQuantity) throws ServiceIntegrationExeption;

}
