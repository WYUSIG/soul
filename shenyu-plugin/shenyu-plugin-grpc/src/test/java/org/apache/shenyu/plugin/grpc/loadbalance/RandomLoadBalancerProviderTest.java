package org.apache.shenyu.plugin.grpc.loadbalance;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import org.apache.shenyu.plugin.grpc.loadbalance.picker.MyHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test(expected = Exception.class)
    public void testNewLoadBalancer() {
        LoadBalancer.Helper helper = new MyHelper();
        LoadBalancer loadBalancer = randomLoadBalancerProvider.newLoadBalancer(helper);

        EquivalentAddressGroup group = mock(EquivalentAddressGroup.class, RETURNS_DEEP_STUBS);
        when(group.getAddresses().isEmpty()).thenReturn(false);
        when(group.getAddresses().toArray()).thenReturn(new Object[]{});

        LoadBalancer.ResolvedAddresses resolvedAddresses = mock(LoadBalancer.ResolvedAddresses.class);
        when(resolvedAddresses.getAddresses()).thenReturn(Arrays.asList(group));

        Attributes attributes = mock(Attributes.class);
        when(attributes.get(GrpcAttributeUtils.appName())).thenReturn("");
        when(resolvedAddresses.getAttributes()).thenReturn(attributes);


        loadBalancer.handleResolvedAddresses(resolvedAddresses);
    }
}
