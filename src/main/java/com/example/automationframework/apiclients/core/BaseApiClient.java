package com.example.automationframework.apiclients.core;

import com.example.automationframework.config.RestClient;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseApiClient {

    @Autowired
    private RestClient restClient;

    private final TokenGenerator tokenGenerator;
    private static final String TOKEN_TYPE = "Bearer ";
    protected static final String AUTHORIZATION_HEADER = "Authorization";

    public BaseApiClient(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }

    protected RequestSpecification getNewClientWithToken(String baseUrl) {
        RequestSpecification freshClient = restClient.createClientWithBaseUrl(baseUrl);
        freshClient.header(AUTHORIZATION_HEADER, getBearerToken());
        return freshClient;
    }

    protected RequestSpecification getNewClientWithoutToken(String baseUrl) {
        return restClient.createClientWithBaseUrl(baseUrl);
    }

    protected String getBearerToken() {
        return TOKEN_TYPE + tokenGenerator.fetchToken();
    }
}
