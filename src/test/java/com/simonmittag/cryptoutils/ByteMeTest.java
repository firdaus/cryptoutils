/*
 * ByteMeTest
 */
package com.simonmittag.cryptoutils;

import com.simonmittag.cryptoutils.ByteHelper;
import junit.framework.TestCase;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author simonmittag
 * @since <version>
 */
public class ByteMeTest extends TestCase {

    public void testByteMeIsAlways16Bytes() throws UnsupportedEncodingException {
        assertTrue(ByteHelper.byteMe(Base64.getEncoder().encodeToString("1111222233334444".getBytes())).length==16);
        assertTrue(ByteHelper.byteMe("1111222233334444").length==16);
        assertTrue(ByteHelper.byteMe("1111222233334444555555555").length==16);
        assertTrue(ByteHelper.byteMe("111122223333").length==16);
        assertTrue(ByteHelper.byteMe("1").length==16);
        assertTrue(ByteHelper.byteMe("").length==16);
    }
}
