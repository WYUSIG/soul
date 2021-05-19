package org.apache.shenyu.plugin.grpc.proto;

import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.stub.StreamObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertTrue;

/**
 * The Test Case For {@link CompositeStreamObserver}.
 */
@RunWith(MockitoJUnitRunner.class)
public class CompositeStreamObserverTest {

    private CompositeStreamObserver<Boolean> compositeStreamObserver;

    private CompleteObserver<Boolean> completeObserver;

    private volatile boolean state = false;

    @Before
    public void setUp() {
        StreamObserver<Boolean> streamObserver = new StreamObserver<Boolean>() {
            @Override
            public void onNext(Boolean value) {
                state = value;
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };
        completeObserver = new CompleteObserver<>();
        compositeStreamObserver = CompositeStreamObserver.of(streamObserver, completeObserver);
    }

    @Test
    public void onCompleted() throws ExecutionException, InterruptedException {
        compositeStreamObserver.onCompleted();
        ListenableFuture future = completeObserver.getCompletionFuture();
        assert future.get() == null;
    }

    @Test(expected = Throwable.class)
    public void onError() throws Exception {
        Throwable throwable = new Throwable("error");
        compositeStreamObserver.onError(throwable);
        ListenableFuture future = completeObserver.getCompletionFuture();
        future.get();
    }

    @Test
    public void onNext() {
        compositeStreamObserver.onNext(true);
        assertTrue(state);
    }
}
