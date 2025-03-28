package com.example.automationframework.pageobjects.core;

import com.example.automationframework.annotations.LazyProtoComponent;

@LazyProtoComponent
public class CommonComponent extends BasePage {

    public void navigateToGivenUrl(String url) {
        seleniumExecutor.getDriver().navigate().to(url);
    }
}
