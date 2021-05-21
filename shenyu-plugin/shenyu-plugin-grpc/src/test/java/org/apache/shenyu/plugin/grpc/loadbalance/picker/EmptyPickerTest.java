package org.apache.shenyu.plugin.grpc.loadbalance.picker;

import io.grpc.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmptyPickerTest {

    @Test
    public void testPickSubchannel() {
        Status status = mock(Status.class);
        when(status.isOk()).thenReturn(true);
        EmptyPicker picker = new EmptyPicker(status);
        Assert.assertNotNull(picker.pickSubchannel(null));
    }

    @Test
    public void testIsEquivalentTo() {
        EmptyPicker picker = new EmptyPicker(mock(Status.class));
        Assert.assertTrue(picker.isEquivalentTo(picker));
    }

    @Test
    public void testGetSubchannelsInfo() {
        EmptyPicker picker = new EmptyPicker(mock(Status.class));
        Assert.assertNotNull(picker.getSubchannelsInfo());
    }
}
