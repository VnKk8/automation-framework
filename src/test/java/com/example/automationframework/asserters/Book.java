package com.example.automationframework.asserters;

import com.example.automationframework.helpers.CustomAsserter;
import com.example.automationframework.models.bookapi.response.GetBookAvailabilityResponse;
import org.junit.jupiter.api.Assertions;

public class Book {

    public static void assertAvailabilityIsUpdated(GetBookAvailabilityResponse response,
                                                   GetBookAvailabilityResponse request,
                                                   int numberOfBooks) {
        Integer expectedQuantity = request.getAvailableCopies() + numberOfBooks;
        Integer actualQuantity = response.getAvailableCopies();
        Assertions.assertAll(
                () -> CustomAsserter.assertStrings(String.valueOf(expectedQuantity),
                        String.valueOf(actualQuantity)),
                () -> CustomAsserter.assertTrue(
                        request.getAvailableCopies() < actualQuantity,
                        "Available copies should increase after the update"
                )
        );
    }

    public static void assertBookIsOrdered(GetBookAvailabilityResponse beforeOrder,
                                                  GetBookAvailabilityResponse afterOrder) {
        Integer expectedQuantity = beforeOrder.getAvailableCopies();
        Integer actualQuantity = afterOrder.getAvailableCopies();
        CustomAsserter.assertTrue(actualQuantity < expectedQuantity, "Available copies should decrease after the update");
    }
}
