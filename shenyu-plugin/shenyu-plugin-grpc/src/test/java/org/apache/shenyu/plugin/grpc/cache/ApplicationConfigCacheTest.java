package org.apache.shenyu.plugin.grpc.cache;

import lombok.SneakyThrows;
import org.apache.shenyu.common.dto.SelectorData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The Test Case For {@link ApplicationConfigCache}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationConfigCacheTest {

    private ApplicationConfigCache applicationConfigCache;

    private SelectorData selector;

    @Before
    public void setUp() {
        applicationConfigCache = ApplicationConfigCache.getInstance();
        selector = mock(SelectorData.class);
        when(selector.getName()).thenReturn("/grpc");
        when(selector.getHandle()).thenReturn("[{\"upstreamUrl\":\"localhost:8080\",\"weight\":50,\"status\":true}]");
    }

    @Test
    public void getInstance() {
        assertNotNull(this.applicationConfigCache);
    }

    @SneakyThrows
    @Test
    public void testGetSize() {
        assertNotNull(ReflectionTestUtils.invokeMethod(this.applicationConfigCache, "getSize"));
    }

    @Test
    public void testInitPrx() {
        this.applicationConfigCache.initPrx(selector);
        assertNotNull(applicationConfigCache.get(selector.getName()));
    }

    @Test
    public void testGet() {
        assertNotNull(this.applicationConfigCache.get("/test"));
    }

    @Test
    public void testInvalidate() {
        this.applicationConfigCache.invalidate(selector.getName());
        assert this.applicationConfigCache.get(selector.getName()).getShenyuServiceInstances().size() == 0;
    }

    @Test
    public void testWatch() {
        this.applicationConfigCache.watch(selector.getName(), Assert::assertNotNull);
    }
}
