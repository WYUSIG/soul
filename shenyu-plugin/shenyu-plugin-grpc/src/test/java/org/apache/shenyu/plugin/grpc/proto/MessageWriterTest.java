package org.apache.shenyu.plugin.grpc.proto;


import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * The Test Case For {@link MessageWriter}.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageWriterTest {

    private JsonFormat.TypeRegistry registry;

    private ShenyuGrpcResponse shenyuGrpcResponse;

    private MessageWriter<Message> messageWriter;

    @Before
    public void setUp() {
        registry = mock(JsonFormat.TypeRegistry.class);
        shenyuGrpcResponse = mock(ShenyuGrpcResponse.class);
        messageWriter = MessageWriter.newInstance(registry, shenyuGrpcResponse);
    }

    @Test
    public void testNewInstance() {
        assertNotNull(MessageWriter.newInstance(registry, shenyuGrpcResponse));
    }

    @Test
    public void testOnNext() throws Exception {
        Message value = mock(Message.class);
        Field field = messageWriter.getClass().getDeclaredField("printer");
        field.setAccessible(true);
        JsonFormat.Printer printer = mock(JsonFormat.Printer.class);
        field.set(messageWriter, printer);
        when(printer.print(value)).thenReturn("grpc");
        messageWriter.onNext(value);
        verify(messageWriter).onNext(value);
    }

    @Test
    public void testOnNextThrowException() throws Exception {
        Message value = mock(Message.class);
        Field field = messageWriter.getClass().getDeclaredField("printer");
        field.setAccessible(true);
        JsonFormat.Printer printer = mock(JsonFormat.Printer.class);
        field.set(messageWriter, printer);
        when(printer.print(value)).thenThrow(InvalidProtocolBufferException.class);
        messageWriter.onNext(value);
        verify(messageWriter).onNext(value);
    }

    @Test
    public void onError() {
        Throwable throwable = mock(Throwable.class);
        messageWriter.onError(throwable);
//        verify(messageWriter).onError(throwable);
    }

    @Test
    public void onCompleted() {
        messageWriter.onCompleted();
//        verify(messageWriter).onCompleted();
    }
}
