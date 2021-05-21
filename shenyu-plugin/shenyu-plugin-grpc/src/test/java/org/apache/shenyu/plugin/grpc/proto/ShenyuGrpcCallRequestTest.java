package org.apache.shenyu.plugin.grpc.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

/**
 * The Test Case For {@link ShenyuGrpcCallRequest}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShenyuGrpcCallRequestTest {

    private ShenyuGrpcCallRequest shenyuGrpcCallRequest;

    @Test
    public void testBuilder() {
        Channel channel = mock(Channel.class);
        CallOptions callOptions = mock(CallOptions.class);
        DynamicMessage dynamicMessage = mock(DynamicMessage.class);
        Descriptors.MethodDescriptor methodDescriptor = mock(Descriptors.MethodDescriptor.class);
        StreamObserver<DynamicMessage> streamObserver = mock(StreamObserver.class);
        shenyuGrpcCallRequest = ShenyuGrpcCallRequest.builder()
                .channel(channel)
                .callOptions(callOptions)
                .requests(dynamicMessage)
                .methodDescriptor(methodDescriptor)
                .responseObserver(streamObserver)
                .build();
        assert shenyuGrpcCallRequest.getChannel().equals(channel) &&
                shenyuGrpcCallRequest.getCallOptions().equals(callOptions) &&
                shenyuGrpcCallRequest.getRequests().equals(dynamicMessage) &&
                shenyuGrpcCallRequest.getMethodDescriptor().equals(methodDescriptor) &&
                shenyuGrpcCallRequest.getResponseObserver().equals(streamObserver);
    }
}
