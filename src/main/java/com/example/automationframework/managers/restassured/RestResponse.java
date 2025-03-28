package com.example.automationframework.managers.restassured;

import com.example.automationframework.wrappers.jakson.ObjectParser;
import io.restassured.response.Response;
import lombok.Getter;

public class RestResponse<T> implements IRestResponse<T> {
    private T data;
    @Getter
    private final Response response;
    @Getter
    private Exception exception;

    public RestResponse(Class<T> tClass, Response response) {
        this.response = response;

        try {
            this.data = tClass.getDeclaredConstructor().newInstance();
        } catch (Exception constructorException) {
            throw new RuntimeException();
        }
    }

    @SuppressWarnings("unchecked")
    public T getBody() {
        try {
            data = (T) ObjectParser.jsonStringToJavaObject(response.getBody().asString(),
                    data.getClass());
        } catch (Exception exception) {
            this.exception = exception;
        }
        return data;
    }

    public String getContent() {
        return response.getBody().asString();
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public boolean isSuccessful() {
        int code = response.getStatusCode();
        return code == 200 || code == 201 || code == 202 || code == 203 || code == 204 || code == 205;
    }

    public String getStatusDescription() {
        return response.getStatusLine();
    }

}
