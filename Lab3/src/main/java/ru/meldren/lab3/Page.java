package ru.meldren.lab3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Page {

    public Page(String url, WebDriver driver) {
        driver.get(url);
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(4))
                .pageLoadTimeout(Duration.ofSeconds(10))
                .scriptTimeout(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
}
