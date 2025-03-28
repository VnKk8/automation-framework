package com.example.automationframework.pageobjects;

import com.example.automationframework.annotations.LazyProtoComponent;
import com.example.automationframework.enums.Header;
import com.example.automationframework.helpers.CustomAsserter;
import com.example.automationframework.pageobjects.core.BasePage;
import com.example.automationframework.pageobjects.core.CommonComponent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Value;

@Getter
@LazyProtoComponent
@RequiredArgsConstructor
public class LoginPage extends BasePage {

    @Value("${application.admin.user}")
    protected String username;
    @Value("${application.admin.password}")
    protected String password;
    private static final By loginButton = By.xpath("//button[@class='submit-btn']");
    private static final By passwordInput = By.xpath("//input[@id='password']");
    private static final By usernameInput = By.xpath("//input[@id='username']");
    private static final By loginForm = By.xpath("//form[@class='login-form']");
    private static final By bookStoreHeader = By.xpath("//div[@class='logo']");
    private static final By loginMenu = By.xpath("//a[@href='/login']");
    private static final By userMenu = By.xpath("//a[@class='dropdown-toggle']");

    private final CommonComponent commonComponent;

    public void  loginSuccessfullyOnLoginPage() {
        openLoginPage();
        verifyLoginPageElements();
        fillFormAndLogin();
        verifyUserIsLoggedIn();
    }

    public void  navigateToLandingPage() {
        commonComponent.navigateToGivenUrl(baseUrl);
    }

    private void openLoginPage() {
        commonComponent.navigateToGivenUrl(baseUrl);
        seleniumExecutor.clickOnElement(loginMenu);
    }

    private void fillFormAndLogin() {
        seleniumExecutor.typeTextOnElement(usernameInput, username);
        seleniumExecutor.typeTextOnElement(passwordInput, password);
        seleniumExecutor.clickOnElement(loginButton);
    }

    private void verifyLoginPageElements() {
        String actualHeader = seleniumExecutor.getTextFromElement(bookStoreHeader).toLowerCase();
        Assertions.assertAll(
                () -> CustomAsserter.assertTrue(seleniumExecutor.isElementDisplayed(loginForm), "Login form is not displayed"),
                () -> CustomAsserter.assertStrings(actualHeader, Header.BOOK_STORE.getValue()));
    }

    private void verifyUserIsLoggedIn() {
        String actualUsername = seleniumExecutor.getTextFromElement(userMenu).toLowerCase();
        CustomAsserter.assertStrings(actualUsername, username);
    }
}
