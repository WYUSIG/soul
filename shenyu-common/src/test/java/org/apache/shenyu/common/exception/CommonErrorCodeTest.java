package org.apache.shenyu.common.exception;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * Test cases for CommonErrorCode.
 */
public class CommonErrorCodeTest {

    @Test
    public void testConstantClass() {
        CommonErrorCode commonErrorCode = new CommonErrorCode();
        Constructor[] constructors = commonErrorCode.getClass().getConstructors();
        assert constructors.length == 1;
    }
}
