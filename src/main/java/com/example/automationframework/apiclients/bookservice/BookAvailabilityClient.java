package com.example.automationframework.apiclients.bookservice;

import com.example.automationframework.annotations.LazyProtoComponent;
import com.example.automationframework.apiclients.core.BaseApiClient;
import com.example.automationframework.apiclients.core.TokenGenerator;
import com.example.automationframework.enums.ApiVersion;
import com.example.automationframework.managers.restassured.IRestResponse;
import com.example.automationframework.managers.restassured.RestResponse;
import com.example.automationframework.models.bookapi.request.PostAvailabilityRequest;
import com.example.automationframework.models.bookapi.response.GetBookAvailabilityResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;

@LazyProtoComponent
public class BookAvailabilityClient extends BaseApiClient {

    @Value("${application.url}")
    protected String baseUrl;

    private static final String ALL = "/all";
    private static final String SAVE = "/save";
    private static final String BOOKS_AVAILABILITY = "/books/availability";

    public BookAvailabilityClient(TokenGenerator tokenGenerator) {
        super(tokenGenerator);
    }

    private RequestSpecification getNewClientWithToken() {
        return getNewClientWithToken(baseUrl);
    }

    public IRestResponse<GetBookAvailabilityResponse> getAllAvailableBooks(boolean withAuth) {
        Response response = (withAuth ? getNewClientWithToken() : getNewClientWithoutToken(baseUrl))
                .get(ApiVersion.V1.getValue() + BOOKS_AVAILABILITY + ALL);

        return new RestResponse<>(GetBookAvailabilityResponse.class, response);
    }

    public IRestResponse<GetBookAvailabilityResponse> getAllAvailableBooksById(boolean withAuth, long bookId) {
        Response response = (withAuth ? getNewClientWithToken() : getNewClientWithoutToken(baseUrl))
                .get(ApiVersion.V1.getValue() + BOOKS_AVAILABILITY + "/" + bookId);

        return new RestResponse<>(GetBookAvailabilityResponse.class, response);
    }

    public IRestResponse<GetBookAvailabilityResponse> postAvailabilitySave(boolean withAuth, PostAvailabilityRequest request) {
        Response response = (withAuth ? getNewClientWithToken() : getNewClientWithoutToken(baseUrl))
                .body(request)
                .post(ApiVersion.V1.getValue() + BOOKS_AVAILABILITY + SAVE);

        return new RestResponse<>(GetBookAvailabilityResponse.class, response);
    }
}
