package org.apache.shenyu.plugin.grpc.resolver;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import org.apache.shenyu.common.dto.MetaData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The Test Case For {@link ServiceResolver}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceResolverTest {

    @Mock
    private ServiceResolver serviceResolver;

    @Before
    public void setUp() {
        DescriptorProtos.FileDescriptorSet descriptorSet = mock(DescriptorProtos.FileDescriptorSet.class);
        serviceResolver = ServiceResolver.fromFileDescriptorSet(descriptorSet);
    }

    @Test
    public void listMessageTypes() {
        ImmutableSet<Descriptors.Descriptor> immutableSet = serviceResolver.listMessageTypes();
        assert immutableSet.size() == 0;
    }

    @Test(expected = IllegalArgumentException.class)
    public void resolveServiceMethod() {
        MetaData metaData = new MetaData();
        metaData.setServiceName("io.EchoService");
        metaData.setMethodName("echo");
        serviceResolver.resolveServiceMethod(metaData);
    }
}
