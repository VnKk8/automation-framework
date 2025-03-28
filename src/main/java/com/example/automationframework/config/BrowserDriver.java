package com.example.automationframework.config;

import com.example.automationframework.annotations.LazyConfiguration;
import com.example.automationframework.managers.WebDriverManager;
import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@LazyConfiguration
public class BrowserDriver {

    @Value("#{ '${headless.mode:}' == '' ? false : '${headless.mode}' }")
    protected boolean isHeadless;

    @Bean
    public WebDriver setupChromeDriver() {
        WebDriver webDriver = OS.isFamilyUnix()
                ? WebDriverManager.createChromeDriver(WebDriverManager.buildUnixChromeOptions())
                : WebDriverManager.createChromeDriver(WebDriverManager.buildChromeOptions(isHeadless));
        WebDriverManager.configureChromeDriver(webDriver);

        return webDriver;
    }
}
