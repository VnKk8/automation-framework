package com.example.automationframework.managers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.List;

public class WebDriverManager {

    private static final List<String> CHROME_VERSIONS = Arrays.asList("dev", "beta", "stable");

    public static ChromeOptions buildChromeOptions(boolean isHeadless) {
        ChromeOptions chromeOptions = createChromeOptions();
        if (isHeadless) {
            getHeadlessChromeOptions(chromeOptions);
        }
        return chromeOptions;
    }

    public static WebDriver createChromeDriver(ChromeOptions options) {
        try {
            return new ChromeDriver(options);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create ChromeDriver", e);
        }
    }

    public static void configureChromeDriver(WebDriver webDriver) {
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();
    }

    private static ChromeOptions createChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setEnableDownloads(true);
        chromeOptions.addArguments("enable-automation");
        chromeOptions.setBrowserVersion(CHROME_VERSIONS.get(2));
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--disable-search-engine-choice-screen");
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
        return chromeOptions;
    }

    private static void getHeadlessChromeOptions(ChromeOptions chromeOptions) {
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless=new");
        chromeOptions.addArguments("--whitelisted-ips");
        chromeOptions.addArguments("--window-size=1920,1080");
    }

    public static ChromeOptions buildUnixChromeOptions() {
        ChromeOptions chromeOptions = createChromeOptions();
        getHeadlessChromeOptions(chromeOptions);

        // memory tracing
        chromeOptions.addArguments("--enable-memory-infra");
        chromeOptions.addArguments("--disabled-by-default-memory-infra");

        // ignore flags
        chromeOptions.addArguments("--ignore-ssl-errors=yes");
        chromeOptions.addArguments("--ignore-certificate-errors");

        // remote flags
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--remote-debugging-port=9222");

        // disable flags
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--dns-prefetch-disable");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-setuid-sandbox");
        chromeOptions.addArguments("--disable-application-cache");
        chromeOptions.addArguments("--disable-background-networking");
        chromeOptions.addArguments("--disable-background-timer-throttling");
        chromeOptions.addArguments("--disable-backgrounding-occluded-windows");

        return chromeOptions;
    }
}
