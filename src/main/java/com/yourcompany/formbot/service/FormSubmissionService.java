package com.yourcompany.formbot.service;

import com.microsoft.playwright.*;
import com.yourcompany.formbot.dto.FormData;
import com.yourcompany.formbot.dto.SubmissionResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FormSubmissionService {
    private final Browser browser;
    @Value("${bot.target-url}") private String targetUrl;
    @Value("${bot.timeout-ms:15000}") private double timeoutMs;
    @Value("${bot.screenshot-directory:storage/screenshots}") private String screenshotDirectory;

    public FormSubmissionService(Browser browser) { this.browser = browser; }

    public SubmissionResult submit(FormData data) {
        try (BrowserContext context = browser.newContext(); Page page = context.newPage()) {
            page.setDefaultTimeout(timeoutMs);
            page.navigate(targetUrl);
            page.locator("#name").fill(data.getName());
            page.locator("#email").fill(data.getEmail());
            page.locator("#category").selectOption(data.getCategory());
            page.locator("#agree-terms").check();
            page.locator("button[type='submit']").click();
            page.locator(".confirmation-message").waitFor();
            return SubmissionResult.success("Form submitted successfully.");
        } catch (Exception ex) {
            return SubmissionResult.failure("Form submission failed: " + ex.getMessage());
        }
    }

    private void ensureScreenshotDirectory() {
        try { Files.createDirectories(Path.of(screenshotDirectory)); } catch (Exception ignored) { }
    }
}
