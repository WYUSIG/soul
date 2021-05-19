package org.apache.shenyu.plugin.grpc.proto;


import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import org.apache.shenyu.plugin.grpc.response.GrpcResponsePlugin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The Test Case For {@link MessageWriter}.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageWriterTest {

    private JsonFormat.TypeRegistry registry;

    private ShenyuGrpcResponse shenyuGrpcResponse;

    @Before
    public void setUp() {
        registry = mock(JsonFormat.TypeRegistry.class);
        shenyuGrpcResponse = mock(ShenyuGrpcResponse.class);
    }

    @Test
    public void testNewInstance() {
        assertNotNull(MessageWriter.newInstance(registry, shenyuGrpcResponse));
    }

//    @Test
//    public void testOnNext() {
//        Message value = mock(Message.class);
//        when(JsonFormat.printer().usingTypeRegistry(registry)).thenReturn(mock(JsonFormat.Printer.class));
//        MessageWriter messageWriter = MessageWriter.newInstance(registry, shenyuGrpcResponse);
//        messageWriter.getClass().getField("printer").setAccessible(true);
//        assertEquals(shenyuGrpcResponse.getResult(), JsonFormat.printer().usingTypeRegistry(registry).print(value));
//    }
}
