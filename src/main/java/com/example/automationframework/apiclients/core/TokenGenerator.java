package com.example.automationframework.apiclients.core;

import com.example.automationframework.enums.ApiVersion;
import com.example.automationframework.managers.restassured.IRestResponse;
import com.example.automationframework.managers.restassured.RestResponse;
import com.example.automationframework.models.AuthenticationRequest;
import com.example.automationframework.models.TokenResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import static io.restassured.RestAssured.given;

public class TokenGenerator {

    private static final String AUTH_LOGIN = "/auth/login";

    @Value("${application.admin.user}")
    protected String username;
    @Value("${application.admin.password}")
    protected String password;
    @Value("${application.url}")
    protected String baseAuthUrl;

    public TokenGenerator(String authUrl, String username, String password) {
        this.baseAuthUrl = authUrl;
        this.username = username;
        this.password = password;
    }

    private AuthenticationRequest authenticationRequest;

    @PostConstruct
    public void init() {
        this.authenticationRequest = new AuthenticationRequest(username, password);
    }

    public String fetchToken() {
        Response response = generateToken();
        IRestResponse<TokenResponse> token = new RestResponse<>(TokenResponse.class, response);

        return token.getContent();
    }

    private Response generateToken() {
        return given()
                .baseUri(baseAuthUrl + ApiVersion.V1.getValue() + AUTH_LOGIN)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(authenticationRequest)
                .post();
    }
}
