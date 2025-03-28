package com.example.automationframework.wrappers.browser.utils;

import com.example.automationframework.annotations.LazyProtoComponent;
import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
@LazyProtoComponent
public class SeleniumWaiter {

    @Value("${default.wait.timeout}")
    private int timeout;
    @Value("${default.polling.interval}")
    private int pollingInterval;
    private final List<Class<? extends Throwable>> ignoredExceptions = new ArrayList<>();

    public WebDriverWait getWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofMillis(this.timeout), Duration.ofMillis(this.pollingInterval));
    }

    @SneakyThrows
    public <T> WebElement waitElement(T elementAttr, WebDriver driver) {
        if (elementAttr instanceof By) {
            if (getUntilByLocatorIsLoadedSuccessfully((By) elementAttr, driver)) {
                return ElementBuilder.buildWebElement((By) elementAttr, driver);
            }
        } else if (elementAttr instanceof WebElement) {
            if (getUntilWebElementIsLoadedSuccessfully((WebElement) elementAttr, driver)) {
                return (WebElement) elementAttr;
            }
        }
        return null;
    }

    private FluentWait<WebDriver> getWebDriverFluentWait(WebDriver driver) {
        return getWebDriverWait(driver).ignoreAll(ignoredExceptions);
    }

    private boolean getUntilByLocatorIsLoadedSuccessfully(By elementAttr, WebDriver driver) {
        return getWebDriverFluentWait(driver).until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(elementAttr),
                ExpectedConditions.elementToBeClickable(elementAttr),
                ExpectedConditions.elementToBeSelected(elementAttr),
                ExpectedConditions.presenceOfElementLocated(elementAttr)));
    }

    private boolean getUntilWebElementIsLoadedSuccessfully(WebElement elementAttr, WebDriver driver) {
        return getWebDriverFluentWait(driver).until(ExpectedConditions.or(
                ExpectedConditions.elementToBeClickable(elementAttr),
                ExpectedConditions.visibilityOf(elementAttr),
                ExpectedConditions.elementToBeSelected(elementAttr)));
    }
}
