package org.apache.shenyu.plugin.grpc.handler;

import org.apache.shenyu.common.dto.SelectorData;
import org.apache.shenyu.common.dto.convert.DivideUpstream;
import org.apache.shenyu.common.enums.PluginEnum;
import org.apache.shenyu.common.utils.GsonUtils;
import org.apache.shenyu.plugin.grpc.cache.ApplicationConfigCache;
import org.apache.shenyu.plugin.grpc.cache.GrpcClientCache;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The Test Case For {@link GrpcPluginDataHandler}.
 */
public class GrpcPluginDataHandlerTest {

    private SelectorData selectorData;

    private GrpcPluginDataHandler grpcPluginDataHandler;

    @Before
    public void setUp() {
        this.grpcPluginDataHandler = new GrpcPluginDataHandler();
        List<DivideUpstream> divideUpstreamList = Stream.of(3)
                .map(weight -> DivideUpstream.builder()
                        .upstreamUrl("localhost:8080" + weight)
                        .build())
                .collect(Collectors.toList());
        this.selectorData = mock(SelectorData.class);
        when(selectorData.getName()).thenReturn("/grpc");
        when(selectorData.getHandle()).thenReturn(GsonUtils.getGson().toJson(divideUpstreamList));
    }

    /**
     * Handler selector test.
     */
    @Test
    public void handlerSelectorTest() {
        grpcPluginDataHandler.handlerSelector(selectorData);
        assertNotNull(GrpcClientCache.getGrpcClient(selectorData.getName()));
//        assert ApplicationConfigCache.getInstance().get(selectorData.getName()).getShenyuServiceInstances().size() != 0;
    }

    /**
     * Remove selector test.
     */
    @Test
    public void removeSelectorTest() {
        grpcPluginDataHandler.removeSelector(selectorData);
        assert ApplicationConfigCache.getInstance().get(selectorData.getName()).getShenyuServiceInstances().size() == 0;
    }

    /**
     * Plugin named test.
     */
    @Test
    public void pluginNamedTest() {
        Assert.assertEquals(grpcPluginDataHandler.pluginNamed(), PluginEnum.GRPC.getName());
    }
}
