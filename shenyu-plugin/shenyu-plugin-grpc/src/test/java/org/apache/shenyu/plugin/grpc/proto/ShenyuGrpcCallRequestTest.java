package org.apache.shenyu.plugin.grpc.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * The Test Case For {@link ShenyuGrpcCallRequest}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShenyuGrpcCallRequestTest {

    private ShenyuGrpcCallRequest shenyuGrpcCallRequest;

    @Test
    public void testBuilder() {
        shenyuGrpcCallRequest = ShenyuGrpcCallRequest.builder()
                .channel(mock(Channel.class))
                .callOptions(mock(CallOptions.class))
                .requests(mock(DynamicMessage.class))
                .methodDescriptor(mock(Descriptors.MethodDescriptor.class))
                .responseObserver(mock(StreamObserver.class))
                .build();
        assertNotNull(shenyuGrpcCallRequest);
    }
}
