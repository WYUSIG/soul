/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.plugin.grpc.proto;

import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
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

    private boolean state = false;

    @Before
    public void setUp() {
        StreamObserver<Boolean> streamObserver = new StreamObserver<Boolean>() {
            @SneakyThrows
            @Override
            public void onNext(final Boolean value) {
                if (!value) {
                    throw new Exception("exception");
                } else {
                    state = true;
                }
            }

            @SneakyThrows
            @Override
            public void onError(final Throwable t) {
                throw new Exception("exception");
            }

            @SneakyThrows
            @Override
            public void onCompleted() {
                throw new Exception("exception");
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

    @Test
    public void onNextThrowException() {
        compositeStreamObserver.onNext(false);
    }
}