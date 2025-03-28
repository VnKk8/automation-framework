package com.example.automationframework.wrappers.browser.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementBuilder {

    public static WebElement buildWebElement(By by, WebDriver driver) {
        return driver.findElement(by);
    }
}
