package com.ensk.exp.mape.integration.tr;

import com.ensk.exp.mape.exception.ServiceIntegrationExeption;
import com.ensk.exp.mape.service.InstrumentPriceProvider;

public class TRInstrumentPriceProvider implements InstrumentPriceProvider {

    public double getSecurityPrices(String securityName) throws ServiceIntegrationExeption {

        if ("ABMX".equals(securityName))
            return 245.40;
        else if ("DFOP".equals(securityName))
            return 42.50;
        else if ("BOGO".equals(securityName))
            return 690.00;
        else {
            throw new ServiceIntegrationExeption(String.format("Unsupported instrument: %s", securityName));
        }

    }

}
