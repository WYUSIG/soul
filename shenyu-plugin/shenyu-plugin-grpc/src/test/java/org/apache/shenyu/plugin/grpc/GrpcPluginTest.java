package org.apache.shenyu.plugin.grpc;

import org.apache.shenyu.common.constant.Constants;
import org.apache.shenyu.common.dto.MetaData;
import org.apache.shenyu.common.dto.RuleData;
import org.apache.shenyu.common.dto.SelectorData;
import org.apache.shenyu.common.enums.PluginEnum;
import org.apache.shenyu.common.enums.RpcTypeEnum;
import org.apache.shenyu.common.exception.ShenyuException;
import org.apache.shenyu.plugin.api.ShenyuPluginChain;
import org.apache.shenyu.plugin.api.context.ShenyuContext;
import org.apache.shenyu.plugin.grpc.cache.ApplicationConfigCache;
import org.apache.shenyu.plugin.grpc.cache.GrpcClientCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The Test Case For {@link GrpcPlugin}.
 */
@RunWith(MockitoJUnitRunner.class)
public class GrpcPluginTest {

    private GrpcPlugin grpcPlugin;

    private ServerWebExchange exchange;

    @Mock
    private ShenyuPluginChain chain;

    private SelectorData selector;

    @Before
    public void setUp() {
        exchange = MockServerWebExchange.from(MockServerHttpRequest.get("http://localhost/grpc/echo").build());
        ShenyuContext shenyuContext = mock(ShenyuContext.class);
        when(shenyuContext.getRpcType()).thenReturn(RpcTypeEnum.GRPC.getName());
        exchange.getAttributes().put(Constants.CONTEXT, shenyuContext);
        MetaData metaData = new MetaData();
        metaData.setId("1332017977771636096");
        metaData.setAppName("grpc");
        metaData.setContextPath("/grpc");
        metaData.setPath("/grpc/echo");
        metaData.setServiceName("echo.EchoService");
        metaData.setMethodName("echo");
        metaData.setRpcType(RpcTypeEnum.GRPC.getName());
        metaData.setRpcExt("{timeout:5000}");
        metaData.setEnabled(true);
        exchange.getAttributes().put(Constants.META_DATA, metaData);
        exchange.getAttributes().put(Constants.PARAM_TRANSFORM, "{message:1}");
        grpcPlugin = new GrpcPlugin();
        selector = mock(SelectorData.class);
        when(selector.getName()).thenReturn("/grpc");
        when(selector.getHandle()).thenReturn("[{\"upstreamUrl\":\"localhost:8080\",\"weight\":50,\"status\":true}]");
        ApplicationConfigCache.getInstance().initPrx(selector);
        GrpcClientCache.initGrpcClient(selector.getName());
    }

    /**
     * expected ShenyuException when examples-grpc is not running
     */
    @Test(expected = ShenyuException.class)
    public void doExecute() {
        RuleData data = mock(RuleData.class);
        StepVerifier.create(grpcPlugin.doExecute(exchange, chain, selector, data)).expectSubscription().verifyComplete();
    }

    @Test
    public void getOrder() {
        final int result = grpcPlugin.getOrder();
        assertEquals(PluginEnum.GRPC.getCode(), result);
    }

    @Test
    public void named() {
        final String result = grpcPlugin.named();
        assertEquals(PluginEnum.GRPC.getName(), result);
    }

    @Test
    public void skip() {
        final Boolean result = grpcPlugin.skip(exchange);
        assertFalse(result);
    }
}
