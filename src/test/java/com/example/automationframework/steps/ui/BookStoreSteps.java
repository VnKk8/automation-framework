package com.example.automationframework.steps.ui;

import com.example.automationframework.enums.CommonKey;
import com.example.automationframework.managers.restassured.IRestResponse;
import com.example.automationframework.models.bookapi.response.GetBookAvailabilityResponse;
import com.example.automationframework.pageobjects.BookStorePage;
import com.google.gson.reflect.TypeToken;
import io.cucumber.java.en.And;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BookStoreSteps extends BaseSteps {

    private final BookStorePage bookStorePage;

    @And("I order a book")
    public void orderABook() {
        GetBookAvailabilityResponse book = getBook(cucumberContext.get(CommonKey.GET_ALL_AVAILABLE_BOOKS));
        bookStorePage.orderBook(book);
    }

    @And("I verify that book is ordered")
    public void verifyBookIsOrdered() {
        GetBookAvailabilityResponse bookBeforeOrder = getBook(cucumberContext.get(CommonKey.GET_ALL_AVAILABLE_BOOKS));
        GetBookAvailabilityResponse bookAfterOrder = cucumberContext.get(CommonKey.BOOK);
        bookStorePage.verifyBookIsOrdered(bookBeforeOrder, bookAfterOrder);
    }

    private GetBookAvailabilityResponse getBook(IRestResponse<GetBookAvailabilityResponse> response) {
        List<GetBookAvailabilityResponse> books = response.getResponse().as(TypeToken.getParameterized(ArrayList.class, GetBookAvailabilityResponse.class).getType());
        GetBookAvailabilityResponse filteredBook = books.stream().findFirst().orElseThrow();
        cucumberContext.saveOrUpdate(CommonKey.BOOK, filteredBook);

        return filteredBook;
    }
}
