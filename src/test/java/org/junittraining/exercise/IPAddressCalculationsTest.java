package org.junittraining.exercise;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * This is a placeholder for your tests!
 */
public class IPAddressCalculationsTest {
    private IPAddressCalculations ipCalc;

    @Before
    public void setUp() {
        //ipCalc = new IPAddressCalculationsHorribleImpl();
        ipCalc = new IPAddressCalculationsImpl();
    }

    // Érvényes jó IP Cím
    @Test
    public void ipAddressToIPNumber_validIP() {
        long result = ipCalc.ipAddressToIPNumber("192.168.1.1");
        assertThat(result).isEqualTo((long) 16777216*192 + 65536*168 + 256 + 1);
    }

    // Érvénytelen IP cím
    @Test(expected = IllegalArgumentException.class)
    public void ipAddressToIPNumber_invalidStandardIP() {
        ipCalc.ipAddressToIPNumber("256.168.1.1");
    }

    // Érvénytelen hosszúságú IP cím
    @Test(expected = IllegalArgumentException.class)
    public void ipAddressToIPNumber_invalidIPLength() {
        ipCalc.ipAddressToIPNumber("256.168.1.1.1");
    }

    // Összetett IP Cím
    @Test
    public void ipAddressToIPNumber_validIPComplex() {
        long result = ipCalc.ipAddressToIPNumber("255.255.255.3");
        assertThat(result).isEqualTo((long) 16777216*255 + 65536*255 + 256*255 + 3);
    }

    // IPv6 cím
    @Test(expected = IllegalArgumentException.class)
    public void ipAddressToIPNumber_invalidStandardIPv6() {
        ipCalc.ipAddressToIPNumber("0000:0000:0000:0000:0000:ffff:c0a8:0101");
    }

    // Null érték megadása
    @Test(expected = IllegalArgumentException.class)
    public void ipAddressToIPNumber_nullInput_throwsException() {
        ipCalc.ipAddressToIPNumber(null);
    }

    // Érvényes, viszont csupa 0
    @Test
    public void ipAddressToIPNumber_minValueIP_returnsZero() {
        assertThat(ipCalc.ipAddressToIPNumber("0.0.0.0")).isEqualTo(0L);
    }
}
