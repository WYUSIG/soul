package org.apache.shenyu.plugin.grpc.resolver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

/**
 * The Test Case For {@link ShenyuResolverHelper}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShenyuResolverHelperTest {

    private ShenyuServiceInstance serviceInstance;

    @Before
    public void setUp() {
        serviceInstance = new ShenyuServiceInstance("localhost", 8080);
    }

    @Test
    public void convertToEquivalentAddressGroup() {
        assertNotNull(ShenyuResolverHelper.convertToEquivalentAddressGroup(serviceInstance));
    }
}
