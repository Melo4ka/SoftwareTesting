package ru.meldren.lab3;

import org.junit.jupiter.api.TestTemplate;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest extends AbstractPageTest {

    @TestTemplate
    void test(WebDriver driver) {
        MainPage page = new MainPage(driver);
        assertEquals("number", page.mainSearchButton.getAttribute("type"));
    }
}
