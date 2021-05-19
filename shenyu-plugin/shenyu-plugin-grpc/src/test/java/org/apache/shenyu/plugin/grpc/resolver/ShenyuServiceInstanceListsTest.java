package org.apache.shenyu.plugin.grpc.resolver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * The Test Case For {@link ShenyuServiceInstanceLists}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShenyuServiceInstanceListsTest {

    private ShenyuServiceInstanceLists shenyuServiceInstanceLists;

    private CopyOnWriteArrayList<ShenyuServiceInstance> shenyuServiceInstances;

    @Before
    public void setUp() {
        shenyuServiceInstances = new CopyOnWriteArrayList<>();
        shenyuServiceInstances.add(mock(ShenyuServiceInstance.class));
        shenyuServiceInstanceLists = new ShenyuServiceInstanceLists(shenyuServiceInstances, "shenyu");
    }

    @Test
    public void NoArgsConstructor() {
        shenyuServiceInstanceLists = new ShenyuServiceInstanceLists();
    }

    @Test
    public void testSet() {
        shenyuServiceInstanceLists.setAppName("shenyu");
        shenyuServiceInstanceLists.setShenyuServiceInstances(shenyuServiceInstances);
        assertEquals(shenyuServiceInstanceLists.getAppName(), "shenyu");
        assertEquals(shenyuServiceInstanceLists.getShenyuServiceInstances(), shenyuServiceInstances);
    }

    @Test
    public void getCopyInstances() {
        List<ShenyuServiceInstance> list = shenyuServiceInstanceLists.getCopyInstances();
        assert list.size() == 1;
    }
}
