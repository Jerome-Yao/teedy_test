package com.sismics.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test HttpUtil.
 */
public class TestHttpUtil {

    @Test
    public void testBuildExpiresHeader() {
        String header = HttpUtil.buildExpiresHeader(3600000); // 1 hour
        Assert.assertNotNull(header);
        Assert.assertTrue(header.startsWith("Sun") || header.startsWith("Mon") || header.startsWith("Tue") ||
                          header.startsWith("Wed") || header.startsWith("Thu") || header.startsWith("Fri") ||
                          header.startsWith("Sat"));
    }

    @Test
    public void testBuildExpiresHeaderZero() {
        String header = HttpUtil.buildExpiresHeader(0);
        Assert.assertNotNull(header);
    }
}
