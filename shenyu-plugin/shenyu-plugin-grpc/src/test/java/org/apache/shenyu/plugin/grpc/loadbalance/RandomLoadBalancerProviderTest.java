package org.apache.shenyu.plugin.grpc.loadbalance;

import io.grpc.LoadBalancer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * The Test Case For {@link RandomLoadBalancerProvider}.
 */
@RunWith(MockitoJUnitRunner.class)
public class RandomLoadBalancerProviderTest {

    private RandomLoadBalancerProvider randomLoadBalancerProvider;

    @Before
    public void setUp() {
        randomLoadBalancerProvider = new RandomLoadBalancerProvider();
    }

    @Test
    public void testIsAvailable() {
        assertTrue(randomLoadBalancerProvider.isAvailable());
    }

    @Test
    public void testGetPriority() {
        assertEquals(randomLoadBalancerProvider.getPriority(), 6);
    }

    @Test
    public void testGetPolicyName() {
        assertEquals(randomLoadBalancerProvider.getPolicyName(), LoadBalancerStrategy.Random.getStrategy());
    }

    @Test
    public void testNewLoadBalancer() {
        LoadBalancer.Helper helper = mock(LoadBalancer.Helper.class);
        LoadBalancer loadBalancer = randomLoadBalancerProvider.newLoadBalancer(helper);
        assertNotNull(loadBalancer);
    }
}
