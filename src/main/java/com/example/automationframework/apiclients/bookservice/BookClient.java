package com.example.automationframework.apiclients.bookservice;

import com.example.automationframework.annotations.LazyProtoComponent;
import com.example.automationframework.apiclients.core.BaseApiClient;
import com.example.automationframework.apiclients.core.TokenGenerator;
import com.example.automationframework.enums.ApiVersion;
import com.example.automationframework.managers.restassured.IRestResponse;
import com.example.automationframework.managers.restassured.RestResponse;
import com.example.automationframework.models.bookapi.request.PostBookRequest;
import com.example.automationframework.models.bookapi.response.PostBookResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;

@LazyProtoComponent
public class BookClient extends BaseApiClient {

    @Value("${application.url}")
    protected String baseUrl;

    private static final String CREATE = "/create";
    private static final String BOOKS = "/books";

    public BookClient(TokenGenerator tokenGenerator) {
        super(tokenGenerator);
    }

    private RequestSpecification getNewClientWithToken() {
        return getNewClientWithToken(baseUrl);
    }

    public IRestResponse<PostBookResponse> postBook(PostBookRequest request, boolean withAuth) {
        Response response = (withAuth ? getNewClientWithToken() : getNewClientWithoutToken(baseUrl))
                .body(request)
                .post(ApiVersion.V1.getValue() + BOOKS + CREATE);

        return new RestResponse<>(PostBookResponse.class, response);
    }
}
