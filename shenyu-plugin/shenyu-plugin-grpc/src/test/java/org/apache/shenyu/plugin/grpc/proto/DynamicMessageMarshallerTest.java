package org.apache.shenyu.plugin.grpc.proto;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The Test Case For {@link DynamicMessageMarshaller}.
 */
@RunWith(MockitoJUnitRunner.class)
public class DynamicMessageMarshallerTest {

    private Descriptors.Descriptor messageDescriptor;

    private DynamicMessageMarshaller dynamicMessageMarshaller;

    @Before
    public void setUp() {
        messageDescriptor = mock(Descriptors.Descriptor.class);
        dynamicMessageMarshaller = new DynamicMessageMarshaller(messageDescriptor);
    }

    @Test(expected = NullPointerException.class)
    public void test() {
        InputStream inputStream = mock(InputStream.class);
        DynamicMessage dynamicMessage = dynamicMessageMarshaller.parse(inputStream);
        InputStream tmp = dynamicMessageMarshaller.stream(dynamicMessage);
        assertEquals(inputStream, tmp);
    }
}
