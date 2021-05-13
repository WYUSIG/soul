package org.apache.shenyu.common.exception;

import org.junit.Test;

/**
 * Test cases for ShenyuException.
 */
public class ShenyuExceptionTest {

    private ShenyuException shenyuException;

    private Throwable throwable = new Throwable();

    private String message = "a serializer exception occurred";

    @Test
    public void ShenyuExceptionWithThrowable() {
        shenyuException = new ShenyuException(throwable);
        assert shenyuException.getCause() != null;
    }

    @Test
    public void ShenyuExceptionWithMessage() {
        shenyuException = new ShenyuException(message);
        assert shenyuException.getMessage().equals(message);
    }

    @Test
    public void ShenyuExceptionWithThrowableAndMessage() {
        shenyuException = new ShenyuException(message, throwable);
        assert shenyuException.getCause() != null &&
                shenyuException.getMessage().equals(message);
    }
}
