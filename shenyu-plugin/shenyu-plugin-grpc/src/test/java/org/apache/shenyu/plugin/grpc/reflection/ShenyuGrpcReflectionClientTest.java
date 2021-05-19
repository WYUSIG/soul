package org.apache.shenyu.plugin.grpc.reflection;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.util.JsonFormat;
import io.grpc.Channel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * The Test Case For {@link ShenyuGrpcReflectionClient}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShenyuGrpcReflectionClientTest {

    private Channel channel;

    private ShenyuGrpcReflectionClient shenyuGrpcReflectionClient;

    private Descriptors.MethodDescriptor methodDescriptor;

    @Before
    public void setUp() {
        channel = mock(Channel.class);
        shenyuGrpcReflectionClient = ShenyuGrpcReflectionClient.create(channel);
        methodDescriptor = mock(Descriptors.MethodDescriptor.class);
    }

    @Test
    public void create() {
        assertNotNull(ShenyuGrpcReflectionClient.create(channel));
    }

    @Test(expected = NullPointerException.class)
    public void lookupService() {
        assertNotNull(shenyuGrpcReflectionClient.lookupService("grpc"));
    }

    @Test(expected = NullPointerException.class)
    public void resolveService() {
        shenyuGrpcReflectionClient.resolveService("grpc");
    }

    @Test(expected = NullPointerException.class)
    public void fetchFullMethodName() {
        shenyuGrpcReflectionClient.fetchFullMethodName(methodDescriptor);
    }

    @Test(expected = NullPointerException.class)
    public void fetchMethodType() {
        shenyuGrpcReflectionClient.fetchMethodType(methodDescriptor);
    }

    @Test(expected = NullPointerException.class)
    public void parseToMessages() {
        JsonFormat.TypeRegistry registry = mock(JsonFormat.TypeRegistry.class);
        Descriptors.Descriptor descriptor = mock(Descriptors.Descriptor.class);
        String jsons = "{'message':'1'}";
        shenyuGrpcReflectionClient.parseToMessages(registry, descriptor, jsons);
    }

    @Test
    public void getFileDescriptorCache() {
        Map<String, DescriptorProtos.FileDescriptorSet> map = shenyuGrpcReflectionClient.getFileDescriptorCache();
        assertNotNull(map);
    }
}
