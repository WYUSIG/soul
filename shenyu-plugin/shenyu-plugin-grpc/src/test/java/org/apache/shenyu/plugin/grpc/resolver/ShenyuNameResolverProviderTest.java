package org.apache.shenyu.plugin.grpc.resolver;

import org.apache.shenyu.common.enums.PluginEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * The Test Case For {@link ShenyuNameResolverProvider}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShenyuNameResolverProviderTest {

    private ShenyuNameResolverProvider shenyuNameResolverProvider;

    @Before
    public void setUp() {
        shenyuNameResolverProvider = new ShenyuNameResolverProvider();
    }

    @Test
    public void getDefaultScheme() {
        assertEquals(shenyuNameResolverProvider.getDefaultScheme(), PluginEnum.GRPC.getName());
    }
}
