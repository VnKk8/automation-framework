package com.example.automationframework.helpers;

import com.example.automationframework.wrappers.jakson.ObjectParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class CustomAsserter {

    public static void assertTrue(boolean expected, String errorMsg) {
        Assertions.assertTrue(expected, errorMsg);
    }

    public static void assertStrings(String expected, String actual) {
        Assertions.assertEquals(expected, actual, String.format("Expected String is: %s while actual String is: %s", expected, actual));
    }

    public static void assertIntegers(int expected, int actual) {
        Assertions.assertEquals(expected, actual, String.format("Expected int value is: %s while actual int value is: %s", expected, actual));
    }

    public static void assertStatusCode(String endpoint, int expectedStatusCode, int actualStatusCode, RequestSpecification rs) {
        RequestSpecificationImpl request = (RequestSpecificationImpl) rs;
        try {
            CustomAsserter.assertIntegers(expectedStatusCode, actualStatusCode);
        } catch (AssertionFailedError e) {
            String errorMessage =
                    String.format("Actual status code is %s instead of %s for %s endpoint with the following request body:", actualStatusCode,
                            expectedStatusCode, endpoint);
            String formattedRequestBody = ObjectParser.javaObjectToFormattedJsonString(request.getBody());
            throw new AssertionFailedError(errorMessage + "\n" + formattedRequestBody, e);
        }
    }

    public static void assertObjects(Object first, Object second) throws JSONException, JsonProcessingException {
        assertMatchedProperties(ObjectParser.javaObjectToJsonString(first), ObjectParser.javaObjectToJsonString(second));
    }

    private static void assertMatchedProperties(String first, String second) throws JSONException {
        JSONAssert.assertEquals(first, second, JSONCompareMode.LENIENT);
    }
}
