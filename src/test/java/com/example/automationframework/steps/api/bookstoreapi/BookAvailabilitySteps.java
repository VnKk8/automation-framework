package com.example.automationframework.steps.api.bookstoreapi;

import com.example.automationframework.apiclients.bookservice.BookAvailabilityClient;
import com.example.automationframework.asserters.Book;
import com.example.automationframework.enums.CommonKey;
import com.example.automationframework.enums.UserType;
import com.example.automationframework.helpers.CustomAsserter;
import com.example.automationframework.managers.restassured.IRestResponse;
import com.example.automationframework.models.bookapi.request.PostAvailabilityRequest;
import com.example.automationframework.models.bookapi.response.GetBookAvailabilityResponse;
import com.example.automationframework.steps.api.BaseApiSteps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.google.gson.reflect.TypeToken;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class BookAvailabilitySteps extends BaseApiSteps {

    private final BookAvailabilityClient bookAvailabilityClient;
    private static final int numberToAdd = Faker.instance().number().numberBetween(1, 100);

    public BookAvailabilitySteps(BookAvailabilityClient bookAvailabilityClient) {
        this.bookAvailabilityClient = bookAvailabilityClient;
    }

    @When("I execute GET books availability all request with {} user")
    public void executeGetAllAvailability(UserType userType) {
        IRestResponse<GetBookAvailabilityResponse> getAllBooksResponse =
                bookAvailabilityClient.getAllAvailableBooks(userType.isValue());
        cucumberContext.saveOrUpdate(CommonKey.RESPONSE, getAllBooksResponse);
        cucumberContext.saveOrUpdate(CommonKey.GET_ALL_AVAILABLE_BOOKS, getAllBooksResponse);
    }

    @When("I execute GET books availability by id request with {} user")
    public void executeGetAvailabilityByBookId(UserType userType) {
        IRestResponse<GetBookAvailabilityResponse> response = cucumberContext.get(CommonKey.GET_ALL_AVAILABLE_BOOKS);
        GetBookAvailabilityResponse book = getBook(response);

        IRestResponse<GetBookAvailabilityResponse> getAllBooksResponse =
                bookAvailabilityClient.getAllAvailableBooksById(userType.isValue(), book.getBookId());
        cucumberContext.saveOrUpdate(CommonKey.GET_AVAILABLE_BOOK_BY_ID, getAllBooksResponse.getBody());
    }

    @When("I execute POST availability save with {} user")
    public void executePostAvailabilitySave(UserType userType) {
        IRestResponse<GetBookAvailabilityResponse> response = cucumberContext.get(CommonKey.GET_ALL_AVAILABLE_BOOKS);
        GetBookAvailabilityResponse book = getBook(response);
        PostAvailabilityRequest request = new PostAvailabilityRequest();
        request.setAvailableCopies(String.valueOf(book.getAvailableCopies() + numberToAdd));
        request.setBookId(book.getBookId());

        IRestResponse<GetBookAvailabilityResponse> postAvailabilitySaveResponse =
                bookAvailabilityClient.postAvailabilitySave(userType.isValue(), request);
        cucumberContext.saveOrUpdate(CommonKey.POST_AVAILABLE_BOOKS_SAVE, postAvailabilitySaveResponse.getBody());
    }

    @Then("I verify that the correct book is found")
    public void verifyCorrectBookIsFound() throws JSONException, JsonProcessingException {
        CustomAsserter.assertObjects(cucumberContext.get(CommonKey.BOOK), cucumberContext.get(CommonKey.GET_AVAILABLE_BOOK_BY_ID));
    }

    @Then("I verify that the available quantity is updated")
    public void verifyUpdatedAvailability() {
        Book.assertAvailabilityIsUpdated(cucumberContext.get(CommonKey.POST_AVAILABLE_BOOKS_SAVE),
                cucumberContext.get(CommonKey.BOOK),
                numberToAdd);
    }

    private GetBookAvailabilityResponse getBook(IRestResponse<GetBookAvailabilityResponse> response) {
        List<GetBookAvailabilityResponse> books = response.getResponse().as(TypeToken.getParameterized(ArrayList.class, GetBookAvailabilityResponse.class).getType());
        GetBookAvailabilityResponse filteredBook = books.stream().findFirst().orElseThrow();
        cucumberContext.saveOrUpdate(CommonKey.BOOK, filteredBook);

        return filteredBook;
    }
}
