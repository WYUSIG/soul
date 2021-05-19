package org.apache.shenyu.plugin.grpc.resolver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * The Test Case For {@link ShenyuServiceInstance}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShenyuServiceInstanceTest {

    private ShenyuServiceInstance shenyuServiceInstance;

    @Before
    public void setUp() {
        shenyuServiceInstance = new ShenyuServiceInstance("localhost", 8080);
    }

    @Test
    public void testWeight() {
        assertEquals(shenyuServiceInstance.getWeight(), 0);
        shenyuServiceInstance.setWeight(1);
        assertEquals(shenyuServiceInstance.getWeight(), 1);
    }

    @Test
    public void testStatus() {
        shenyuServiceInstance.setStatus(true);
        assertEquals(shenyuServiceInstance.getStatus(), "true");
    }
}
