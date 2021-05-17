package org.apache.shenyu.plugin.grpc.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * The Test Case For {@link GrpcClientCache}.
 */
@RunWith(MockitoJUnitRunner.class)
public class GrpcClientCacheTest {

    private final String contextPath = "/grpc";

    @Test
    public void testInitAndGetGrpcClient() {
        GrpcClientCache.initGrpcClient(contextPath);
        assertNotNull(GrpcClientCache.getGrpcClient(contextPath));
    }

    @Test
    public void testRemoveClient() {
        GrpcClientCache.removeClient(contextPath);
        assertNull(GrpcClientCache.getGrpcClient(contextPath));
    }
}
