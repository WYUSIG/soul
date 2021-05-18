package org.apache.shenyu.plugin.grpc.loadbalance.picker;

import io.grpc.Attributes;
import io.grpc.LoadBalancer;
import org.apache.shenyu.plugin.grpc.loadbalance.SubChannelCopy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The Test Case For {@link RandomPicker}.
 */
@RunWith(MockitoJUnitRunner.class)
public class RandomPickerTest {

    private RandomPicker randomPicker;

    private List<LoadBalancer.Subchannel> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        LoadBalancer.Subchannel subchannel = mock(LoadBalancer.Subchannel.class);
        when(subchannel.getAttributes()).thenReturn(mock(Attributes.class));
        list.add(subchannel);
        randomPicker = new RandomPicker(list);
    }

//    @Test
//    public void testPick() {
//        final List<SubChannelCopy> subChannelCopyList = randomPicker.getSubchannels();
//        assertNotNull(randomPicker.pick(subChannelCopyList));
//    }
}
