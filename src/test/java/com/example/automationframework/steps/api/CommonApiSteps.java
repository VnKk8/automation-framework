package com.example.automationframework.steps.api;

import com.example.automationframework.enums.CommonKey;
import com.example.automationframework.helpers.CustomAsserter;
import com.example.automationframework.managers.restassured.IRestResponse;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;

public class CommonApiSteps extends BaseApiSteps {

    @Then("I verify {string} response code is {int}")
    public void verifyResponseCode(String endpoint, int expectedStatusCode) {
        IRestResponse<?> restResponse = cucumberContext.get(CommonKey.RESPONSE);
        RequestSpecification requestSpecification = cucumberContext.get(CommonKey.REQUEST_SPECIFICATION);
        int actualStatusCode = restResponse.getStatusCode();
        CustomAsserter.assertStatusCode(endpoint, expectedStatusCode, actualStatusCode, requestSpecification);
    }
}
