package com.yourcompany.formbot.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaywrightConfig {
    @Bean(destroyMethod = "close")
    public Playwright playwright() { return Playwright.create(); }

    @Bean(destroyMethod = "close")
    public Browser browser(Playwright playwright, @Value("${playwright.headless:false}") boolean headless) {
        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
    }
}
