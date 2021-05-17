package org.apache.shenyu.plugin.grpc.context;

import org.apache.shenyu.common.dto.MetaData;
import org.apache.shenyu.common.enums.PluginEnum;
import org.apache.shenyu.plugin.api.context.ShenyuContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The Test Case For {@link GrpcShenyuContextDecorator}.
 */
public class GrpcShenyuContextDecoratorTest {

    private GrpcShenyuContextDecorator grpcShenyuContextDecorator;

    @Before
    public void setup() {
        grpcShenyuContextDecorator = new GrpcShenyuContextDecorator();
    }

    @Test
    public void testDecorator() {
        MetaData metaData = new MetaData();
        metaData.setAppName("grpc");
        metaData.setServiceName("echo");
        metaData.setRpcType(PluginEnum.GRPC.getName());
        metaData.setContextPath("/grpc");
        assert grpcShenyuContextDecorator.decorator(new ShenyuContext(), metaData) != null;
    }

    @Test
    public void testRpcType() {
        assertEquals(grpcShenyuContextDecorator.rpcType(), PluginEnum.GRPC.getName());
    }
}
