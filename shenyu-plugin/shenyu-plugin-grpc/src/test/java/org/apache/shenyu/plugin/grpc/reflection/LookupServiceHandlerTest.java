package org.apache.shenyu.plugin.grpc.reflection;

import io.grpc.reflection.v1alpha.ServerReflectionRequest;
import io.grpc.reflection.v1alpha.ServerReflectionResponse;
import io.grpc.stub.StreamObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
