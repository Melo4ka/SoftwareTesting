package ru.meldren.lab3.bongacams;

import org.junit.jupiter.api.TestTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.meldren.lab3.AbstractPageTest;
import ru.meldren.lab3.Constant;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class AgeConfirmationTest extends AbstractPageTest {

    @TestTemplate
    void testAdultConfirmation(WebDriver driver) {
        AgeConfirmationPage page = new AgeConfirmationPage(driver);
        initDriver(Constant.BASE_URI, driver);

        page.confirmIsAdult();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        assertTrue(wait.until(invisibilityOf(page.getAgeConfirmationPopup())));
    }

    @TestTemplate
    void testUnderAgeConfirmation(WebDriver driver) {
        AgeConfirmationPage page = new AgeConfirmationPage(driver);
        initDriver(Constant.BASE_URI, driver);

        page.confirmIsUnderAge();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        assertTrue(wait.until(numberOfWindowsToBe(2)));
    }
}
