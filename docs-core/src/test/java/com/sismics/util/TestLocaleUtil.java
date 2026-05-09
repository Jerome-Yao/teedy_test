package com.sismics.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * Test LocaleUtil.
 */
public class TestLocaleUtil {

    @Test
    public void testGetLocaleNull() {
        Locale locale = LocaleUtil.getLocale(null);
        Assert.assertEquals(Locale.ENGLISH, locale);
    }

    @Test
    public void testGetLocaleEmpty() {
        Locale locale = LocaleUtil.getLocale("");
        Assert.assertEquals(Locale.ENGLISH, locale);
    }

    @Test
    public void testGetLocaleLanguageOnly() {
        Locale locale = LocaleUtil.getLocale("fr");
        Assert.assertEquals(new Locale("fr"), locale);
    }

    @Test
    public void testGetLocaleLanguageCountry() {
        Locale locale = LocaleUtil.getLocale("fr_FR");
        Assert.assertEquals(new Locale("fr", "FR"), locale);
    }

    @Test
    public void testGetLocaleLanguageCountryVariant() {
        Locale locale = LocaleUtil.getLocale("fr_FR_PARIS");
        Assert.assertEquals(new Locale("fr", "FR", "PARIS"), locale);
    }
}
