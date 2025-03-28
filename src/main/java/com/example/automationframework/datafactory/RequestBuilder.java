package com.example.automationframework.datafactory;

import com.example.automationframework.models.bookapi.request.PostBookRequest;
import com.github.javafaker.Faker;

public class RequestBuilder {

    public static PostBookRequest createPostBookRequest() {
        PostBookRequest request = new PostBookRequest();
        request.setTitle(Faker.instance().book().title());
        request.setAuthor(Faker.instance().book().author());
        request.setDescription(Faker.instance().book().author());
        request.setPrice(100);
        request.setPublisher(Faker.instance().book().publisher());
        request.setPublishedDate("2025-03-26");
        request.setPages(250);
        request.setAvailableCopies(1000);
        return request;
    }
}
