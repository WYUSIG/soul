package org.apache.shenyu.plugin.grpc.loadbalance.picker;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import lombok.SneakyThrows;
import org.apache.shenyu.plugin.grpc.loadbalance.SubChannelCopy;
import org.apache.shenyu.plugin.grpc.loadbalance.SubChannels;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * The Test Case For {@link RandomPicker}.
 */
@RunWith(MockitoJUnitRunner.class)
public class RandomPickerTest {

    private RandomPicker randomPicker;

    private List<LoadBalancer.Subchannel> list;

    @Before
    @SneakyThrows
    public void setUp() {
        Attributes attributes = SubChannels.createAttributes(1, "ok");
        LoadBalancer.Subchannel subchannel =
                SubChannels.createSubChannel(new MyHelper(), mock(EquivalentAddressGroup.class), attributes);
        list = new LinkedList<>();
        list.add(subchannel);
        randomPicker = new RandomPicker(list);
    }

    @Test
    public void testPickSubchannel() {
        Assert.assertNotNull(randomPicker.pickSubchannel(null));
    }

    @Test
    public void testIsEquivalentTo() {
        Assert.assertTrue(randomPicker.isEquivalentTo(randomPicker));
    }

    @Test
    public void testGetSubchannelsInfo() {
        Assert.assertNotNull(randomPicker.getSubchannelsInfo());
    }

    @Test
    public void testPick() {
        SubChannelCopy firstSubChannelCopy = mock(SubChannelCopy.class);
        SubChannelCopy secondSubChannelCopy = mock(SubChannelCopy.class);
        List<SubChannelCopy> list = Arrays.asList(firstSubChannelCopy, secondSubChannelCopy);
        Assert.assertNotNull(randomPicker.pick(list));
        Assert.assertEquals(firstSubChannelCopy, randomPicker.pick(Arrays.asList(firstSubChannelCopy)));
        Assert.assertNull(randomPicker.pick(null));
    }
}
