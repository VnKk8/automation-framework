package com.example.automationframework.steps.api.bookstoreapi;

import com.example.automationframework.apiclients.bookservice.BookClient;
import com.example.automationframework.datafactory.RequestBuilder;
import com.example.automationframework.enums.CommonKey;
import com.example.automationframework.enums.UserType;
import com.example.automationframework.managers.restassured.IRestResponse;
import com.example.automationframework.models.bookapi.request.PostBookRequest;
import com.example.automationframework.models.bookapi.response.PostBookResponse;
import com.example.automationframework.steps.api.BaseApiSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class BookSteps extends BaseApiSteps {

    private final BookClient bookClient;

    public BookSteps(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    @Given("I create POST book request")
    public void createPostBookRequest() {
        PostBookRequest newBook = RequestBuilder.createPostBookRequest();
        cucumberContext.saveOrUpdate(CommonKey.BOOK_REQUEST, newBook);
    }

    @When("I execute POST book create with {} user")
    public void executePostBookCreate(UserType userType) {
        IRestResponse<PostBookResponse> response =
                bookClient.postBook(cucumberContext.get(CommonKey.BOOK_REQUEST), userType.isValue());
        cucumberContext.saveOrUpdate(CommonKey.RESPONSE, response);
        cucumberContext.saveOrUpdate(CommonKey.BOOK, response.getBody());
    }
}
