package com.ensk.exp.mape.service;

import com.ensk.exp.mape.exception.ServiceIntegrationExeption;

public interface InstrumentPriceProvider {

	double getSecurityPrices(String securityName) throws ServiceIntegrationExeption;

}
