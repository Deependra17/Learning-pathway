package org.pathway;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class BrokenLinks {
    private WebDriver driver;

    @Test
    public void testGetOnlyBrokenLinks() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
        driver.get("https://reader-dot-lets-read-dev.uc.r.appspot.com/"); // Open the website

        // List to store extracted URLs
        List<String> urls = new ArrayList<>(); // Replace with your logic to populate URLs

        // List to store broken links
        List<String> brokenLinks = new ArrayList<>();

        // Loop through extracted URLs and attempt to fetch each one
        for (String url : urls) {
            if (isBrokenLink(url)) {
                brokenLinks.add(url);
            }
        }

        // Print broken links to the console
        System.out.println("Broken Links:");
        for (String brokenLink : brokenLinks) {
            System.out.println(brokenLink);
        }
    }

    private boolean isBrokenLink(String url) {
        try {
            // Attempt to fetch the URL
            driver.get(url);
            // If the URL is successfully fetched, it is not considered broken
            return false;
        } catch (Exception e) {
            // If an exception occurs while fetching the URL, it is considered broken
            return true;
        }
    }


}
