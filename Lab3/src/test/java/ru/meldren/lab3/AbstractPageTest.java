package ru.meldren.lab3;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.seljup.SingleSession;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPageTest {

    @RegisterExtension
    static final SeleniumJupiter SELENIUM_JUPITER = new SeleniumJupiter();

    @BeforeAll
    static void setup() {
        for (Browser browser : Browser.values()) {
            SELENIUM_JUPITER.addBrowsers(browser.createBrowser());
        }
    }
}
