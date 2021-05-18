package org.apache.shenyu.plugin.grpc.loadbalance;

import io.grpc.LoadBalancer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * The Test Case For {@link RoundRobinLoadBalancerProvider}.
 */
@RunWith(MockitoJUnitRunner.class)
public class RoundRobinLoadBalancerProviderTest {

    private RoundRobinLoadBalancerProvider roundRobinLoadBalancerProvider;

    @Before
    public void setUp() {
        roundRobinLoadBalancerProvider = new RoundRobinLoadBalancerProvider();
    }

    @Test
    public void testIsAvailable() {
        assertTrue(roundRobinLoadBalancerProvider.isAvailable());
    }

    @Test
    public void testGetPriority() {
        assertEquals(roundRobinLoadBalancerProvider.getPriority(), 6);
    }

    @Test
    public void testGetPolicyName() {
        assertEquals(roundRobinLoadBalancerProvider.getPolicyName(), LoadBalancerStrategy.Random.getStrategy());
    }

    @Test
    public void testNewLoadBalancer() {
        LoadBalancer.Helper helper = mock(LoadBalancer.Helper.class);
        LoadBalancer loadBalancer = roundRobinLoadBalancerProvider.newLoadBalancer(helper);
        assertNotNull(loadBalancer);
    }
}
