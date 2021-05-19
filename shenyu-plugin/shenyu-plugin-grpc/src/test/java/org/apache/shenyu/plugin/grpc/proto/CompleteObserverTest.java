package org.apache.shenyu.plugin.grpc.proto;

import com.google.common.util.concurrent.ListenableFuture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.ExecutionException;

/**
 * The Test Case For {@link CompleteObserver}.
 */
@RunWith(MockitoJUnitRunner.class)
public class CompleteObserverTest {

    private CompleteObserver completeObserver;

    @Before
    public void setUp() {
        completeObserver = new CompleteObserver();
    }

    @Test
    public void onCompleted() throws ExecutionException, InterruptedException {
        completeObserver.onCompleted();
        ListenableFuture future = completeObserver.getCompletionFuture();
        assert future.get() == null;
    }

    @Test(expected = Throwable.class)
    public void onError() throws Exception {
        Throwable throwable = new Throwable("error");
        completeObserver.onError(throwable);
        ListenableFuture future = completeObserver.getCompletionFuture();
        future.get();
    }

    @Test
    public void onNext() {
        completeObserver.onNext(new Object());
    }
}
