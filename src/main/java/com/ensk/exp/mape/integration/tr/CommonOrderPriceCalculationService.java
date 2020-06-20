package com.ensk.exp.mape.integration.tr;

import com.ensk.exp.mape.exception.ServiceIntegrationExeption;
import com.ensk.exp.mape.integration.st.EQOrderCalculationStrategy;
import com.ensk.exp.mape.integration.st.FIOrderCalculationStrategy;
import com.ensk.exp.mape.service.InstrumentPriceProvider;
import com.ensk.exp.mape.service.OrderCalculationStrategy;
import com.ensk.exp.mape.service.OrderPriceCalculationService;
import com.ensk.exp.mape.util.FormatUtil;

import java.util.HashMap;
import java.util.Map;

public class CommonOrderPriceCalculationService implements OrderPriceCalculationService {

    private static final InstrumentPriceProvider instrumentPriceProvider = new TRInstrumentPriceProvider();

    private static final Map orderCalculationStrategyMap = new HashMap<String, OrderPriceCalculationService>();

    static {
        orderCalculationStrategyMap.put("EQ", new EQOrderCalculationStrategy());
        orderCalculationStrategyMap.put("FI", new FIOrderCalculationStrategy());
    }

    @Override
    public double getOrderPrice(String instrumentName, String instrumentType, String tradeDirection, double tradeQuantity)
            throws ServiceIntegrationExeption {
        OrderCalculationStrategy orderPriceCalculationService = getOrderCalculationStrategy(instrumentType);
        orderPriceCalculationService.checkQuantity(tradeDirection, tradeQuantity);
        double instrumentPrice = instrumentPriceProvider.getSecurityPrices(instrumentName);
        double orderCommission = orderPriceCalculationService.getOrderCommission(tradeDirection);
        return FormatUtil.setDecimalScale(calculateOrderPrice(instrumentPrice, tradeQuantity, orderCommission));
    }

    private double calculateOrderPrice(double instrumentPrice, double tradeQuantity, double orderCommission) {
        return instrumentPrice * tradeQuantity * (1 + orderCommission);
    }

    private OrderCalculationStrategy getOrderCalculationStrategy(String instrumentType) throws ServiceIntegrationExeption {
        OrderCalculationStrategy strategy = (OrderCalculationStrategy)orderCalculationStrategyMap.get(instrumentType);
        if (null == strategy) {
            throw new ServiceIntegrationExeption(String.format("Unsupported instrumentType: %s", instrumentType));
        }
        return strategy;
    }

}
