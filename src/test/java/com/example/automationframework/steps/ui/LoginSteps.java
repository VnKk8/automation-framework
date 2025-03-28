package com.example.automationframework.steps.ui;

import com.example.automationframework.pageobjects.LoginPage;
import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginSteps extends BaseSteps {

    private final LoginPage loginPage;

    @Given("I navigate to Login page")
    public void navigateToLoginPage() {
        loginPage.loginSuccessfullyOnLoginPage();
    }

    @Given("I navigate to Landing page")
    public void navigateToLandingPage() {
        loginPage.navigateToLandingPage();
    }
}
