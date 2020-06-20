package com.ensk.exp.mape.integration.tr;

import com.ensk.exp.mape.exception.ServiceIntegrationExeption;
import com.ensk.exp.mape.service.InstrumentPriceProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class TRInstrumentPriceProviderTest {

    // initial letter logogram of methods
    enum Type {
        GSP, GSPE
    }

    private Type type;
    String securityName;
    private InstrumentPriceProvider instrumentPriceProvider;

    public TRInstrumentPriceProviderTest(Type type, String securityName) {
        this.type = type;
        this.securityName = securityName;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                        {Type.GSP, "ABMX"},
                        {Type.GSP, "DFOP"},
                        {Type.GSP, "BOGO"},
                        {Type.GSPE, "HILG"},
                        {Type.GSPE, "1E3T"},
                        {Type.GSPE, ""},
                        {Type.GSPE, null}});
    }

    @Before
    public void setUp() {
        instrumentPriceProvider = new TRInstrumentPriceProvider();
    }

    @After
    public void tearDown() {}

    @Test
    public void testGetSecurityPrices() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.GSP);
        Assert.assertTrue(instrumentPriceProvider.getSecurityPrices(securityName) > 0);
    }

    @Test(expected = ServiceIntegrationExeption.class)
    public void testGetSecurityPricesException() throws ServiceIntegrationExeption {
        Assume.assumeTrue(type == Type.GSPE);
        Assert.assertTrue(instrumentPriceProvider.getSecurityPrices(securityName) > 0);
    }
}
