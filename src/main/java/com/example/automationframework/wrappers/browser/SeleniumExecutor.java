package com.example.automationframework.wrappers.browser;

import com.example.automationframework.annotations.LazyProtoComponent;
import com.example.automationframework.wrappers.browser.utils.SeleniumWaiter;
import com.example.automationframework.wrappers.utils.BySelector;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter
@LazyProtoComponent
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SeleniumExecutor {

    private final WebDriver driver;
    private final SeleniumWaiter seleniumWaiter;

    public <T> void clickOnElement(T elementAttr) {
        seleniumWaiter.waitElement(elementAttr, driver).click();
    }

    public void clickOnElement(String locator, String dynamicParam) {
        By updatedLocator = BySelector.locatorParser(locator, dynamicParam);
        seleniumWaiter.waitElement(updatedLocator, driver).click();
    }

    public <T> boolean isElementDisplayed(T elementAttr) {
        return seleniumWaiter.waitElement(elementAttr, driver).isDisplayed();
    }

    public <T> void typeTextOnElement(T elementAttr, String text) {
        seleniumWaiter.waitElement(elementAttr, driver).sendKeys(text);
    }

    public <T> String getTextFromElement(T elementAttr) {
        return seleniumWaiter.waitElement(elementAttr, driver).getText();
    }
}
