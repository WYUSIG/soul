package org.apache.shenyu.common.exception;

import org.junit.Test;

/**
 * Test cases for SerializerException.
 */
public class SerializerExceptionTest {

    private SerializerException serializerException;

    private Throwable throwable = new Throwable();

    private String message = "a serializer exception occurred";

    @Test
    public void SerializerExceptionWithThrowable() {
        serializerException = new SerializerException(throwable);
        assert serializerException.getCause() != null;
    }

    @Test
    public void SerializerExceptionWithMessage() {
        serializerException = new SerializerException(message);
        assert serializerException.getMessage().equals(message);
    }

    @Test
    public void SerializerExceptionWithThrowableAndMessage() {
        serializerException = new SerializerException(message, throwable);
        assert serializerException.getCause() != null &&
                serializerException.getMessage().equals(message);
    }
}