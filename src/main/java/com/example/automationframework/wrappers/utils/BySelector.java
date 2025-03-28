package com.example.automationframework.wrappers.utils;

import org.openqa.selenium.By;

public class BySelector {

    public static By locatorParser(String locator, String replacement) {
        String updatedLocator = String.format(locator, replacement);
        return By.xpath(updatedLocator);
    }
}
