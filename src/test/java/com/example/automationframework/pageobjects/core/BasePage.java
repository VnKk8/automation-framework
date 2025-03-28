package com.example.automationframework.pageobjects.core;

import com.example.automationframework.context.CucumberContext;
import com.example.automationframework.wrappers.browser.SeleniumExecutor;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Getter
public abstract class BasePage {
    @Value("${application.url}")
    protected String baseUrl;
    @Autowired
    protected SeleniumExecutor seleniumExecutor;
    @Autowired
    protected CucumberContext cucumberContext;

    @PostConstruct
    private void initPage() {
        PageFactory.initElements(this.seleniumExecutor.getDriver(), this);
    }
}
