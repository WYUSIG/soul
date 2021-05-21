package org.apache.shenyu.plugin.grpc.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * The Test Case For {@link ShenyuGrpcRequest}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShenyuGrpcRequestTest {

    private ShenyuGrpcRequest shenyuGrpcRequest;

    @Test
    public void testBuilder() {
        Descriptors.MethodDescriptor methodDescriptor = mock(Descriptors.MethodDescriptor.class);
        Channel channel = mock(Channel.class);
        CallOptions callOptions = mock(CallOptions.class);
        List<DynamicMessage> requests = mock(List.class);
        StreamObserver<DynamicMessage> streamObserver = mock(StreamObserver.class);
        shenyuGrpcRequest = ShenyuGrpcRequest.builder()
                .methodDescriptor(methodDescriptor)
                .channel(channel)
                .callOptions(callOptions)
                .requests(requests)
                .responseObserver(streamObserver)
                .build();
        assert shenyuGrpcRequest.getChannel().equals(channel) &&
                shenyuGrpcRequest.getCallOptions().equals(callOptions) &&
                shenyuGrpcRequest.getRequests().equals(requests) &&
                shenyuGrpcRequest.getMethodDescriptor().equals(methodDescriptor) &&
                shenyuGrpcRequest.getResponseObserver().equals(streamObserver);
    }
}
