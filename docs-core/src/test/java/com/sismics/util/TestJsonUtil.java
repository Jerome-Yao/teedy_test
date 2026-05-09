package com.sismics.util;

import jakarta.json.JsonValue;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test the JSON utilities.
 */
public class TestJsonUtil {

    @Test
    public void testNullableString() {
        Assert.assertEquals(JsonValue.NULL, JsonUtil.nullable((String) null));
        JsonValue result = JsonUtil.nullable("hello");
        Assert.assertEquals("hello", result.toString().replace("\"", ""));
    }

    @Test
    public void testNullableInteger() {
        Assert.assertEquals(JsonValue.NULL, JsonUtil.nullable((Integer) null));
        JsonValue result = JsonUtil.nullable(Integer.valueOf(42));
        Assert.assertEquals("42", result.toString());
    }

    @Test
    public void testNullableLong() {
        Assert.assertEquals(JsonValue.NULL, JsonUtil.nullable((Long) null));
        JsonValue result = JsonUtil.nullable(Long.valueOf(123L));
        Assert.assertEquals("123", result.toString());
    }
}
