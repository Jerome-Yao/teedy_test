package com.sismics.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test EnvironmentUtil.
 */
public class TestEnvironmentUtil {

    @Test
    public void testIsWindows() {
        boolean isWindows = EnvironmentUtil.isWindows();
    }

    @Test
    public void testIsMacOs() {
        boolean isMacOs = EnvironmentUtil.isMacOs();
    }

    @Test
    public void testIsUnix() {
        boolean isUnix = EnvironmentUtil.isUnix();
    }

    @Test
    public void testIsUnitTest() {
        Assert.assertTrue(EnvironmentUtil.isUnitTest());
    }

    @Test
    public void testIsDevMode() {
        // Dev mode depends on system property, test just verifies method runs
        EnvironmentUtil.isDevMode();
    }

    @Test
    public void testGetMacOsUserHome() {
        String home = EnvironmentUtil.getMacOsUserHome();
        Assert.assertNotNull(home);
    }

    @Test
    public void testWebappContext() {
        Assert.assertFalse(EnvironmentUtil.isWebappContext());
        EnvironmentUtil.setWebappContext(true);
        Assert.assertTrue(EnvironmentUtil.isWebappContext());
        EnvironmentUtil.setWebappContext(false);
    }
}
