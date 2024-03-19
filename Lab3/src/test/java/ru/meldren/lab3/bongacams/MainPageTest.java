package ru.meldren.lab3.bongacams;

import org.junit.jupiter.api.TestTemplate;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.meldren.lab3.AbstractPageTest;
import ru.meldren.lab3.Constant;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MainPageTest extends AbstractPageTest {

    @TestTemplate
    void testCorrectLogin(WebDriver driver) {
        AgeConfirmationPage agePage = new AgeConfirmationPage(driver);
        MainPage mainPage = new MainPage(driver);
        initDriver(Constant.BASE_URI, driver);

        agePage.confirmIsAdult();
        mainPage.login(Constant.USERNAME, Constant.PASSWORD);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(visibilityOf(mainPage.getUsernameContainer()));

        assertEquals(Constant.USERNAME, mainPage.getUsername());
    }

    @TestTemplate
    void testIncorrectLogin(WebDriver driver) {
        AgeConfirmationPage agePage = new AgeConfirmationPage(driver);
        MainPage mainPage = new MainPage(driver);
        initDriver(Constant.BASE_URI, driver);

        agePage.confirmIsAdult();
        String incorrectPassword = Constant.PASSWORD + "123";
        mainPage.login(Constant.USERNAME, incorrectPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        assertThrows(TimeoutException.class, () -> wait.until(visibilityOf(mainPage.getUsernameContainer())));
    }

    @TestTemplate
    void testQuickSearch(WebDriver driver) {
        AgeConfirmationPage agePage = new AgeConfirmationPage(driver);
        MainPage mainPage = new MainPage(driver);
        initDriver(Constant.BASE_URI, driver);

        agePage.confirmIsAdult();
        mainPage.processQuickSearch(MainPage.GenderCategory.MEN);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        assertDoesNotThrow(() -> wait.until(visibilityOf(mainPage.getQuickSearchResult())));
    }

    @TestTemplate
    void testFriendInvitation(WebDriver driver) {
        AgeConfirmationPage agePage = new AgeConfirmationPage(driver);
        MainPage mainPage = new MainPage(driver);
        initDriver(Constant.BASE_URI, driver);

        agePage.confirmIsAdult();
        mainPage.login(Constant.USERNAME, Constant.PASSWORD);
        mainPage.inviteFriend("abcdef123@gmail.com");

        assertTrue(mainPage.isFriendInvitationSent());
    }
}
