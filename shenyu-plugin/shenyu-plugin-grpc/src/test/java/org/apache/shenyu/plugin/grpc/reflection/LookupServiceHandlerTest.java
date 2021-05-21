package org.apache.shenyu.plugin.grpc.reflection;

import com.google.protobuf.ByteString;
import io.grpc.reflection.v1alpha.FileDescriptorResponse;
import io.grpc.reflection.v1alpha.ServerReflectionRequest;
import io.grpc.reflection.v1alpha.ServerReflectionResponse;
import io.grpc.stub.StreamObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The Test Case For {@link LookupServiceHandler}.
 */
@RunWith(MockitoJUnitRunner.class)
public class LookupServiceHandlerTest {

    private LookupServiceHandler lookupServiceHandler;
    @Mock
    private StreamObserver<ServerReflectionRequest> requestStream;
    @Mock
    private ServerReflectionResponse response;
    @Mock
    private Throwable throwable;

    @Before
    public void setUp() {
        lookupServiceHandler = new LookupServiceHandler("/grpc");
        when(response.getMessageResponseCase()).thenReturn(ServerReflectionResponse.MessageResponseCase.FILE_DESCRIPTOR_RESPONSE);
        when(response.getFileDescriptorResponse()).thenReturn(mock(FileDescriptorResponse.class));
        when(response.getFileDescriptorResponse().getFileDescriptorProtoList()).thenReturn(new ArrayList<>());
    }

    @Test
    public void start() {
        lookupServiceHandler.start(requestStream);
    }

    @Test
    public void onNext() {
        lookupServiceHandler.onNext(response);
    }

    @Test
    public void onError() {
        lookupServiceHandler.onError(throwable);
    }
}
