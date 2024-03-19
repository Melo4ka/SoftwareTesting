package ru.meldren.lab3.bongacams;

import org.junit.jupiter.api.TestTemplate;
import org.openqa.selenium.WebDriver;
import ru.meldren.lab3.AbstractPageTest;
import ru.meldren.lab3.Constant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest extends AbstractPageTest {

    @TestTemplate
    void testViewFirstModel(WebDriver driver) {
        AgeConfirmationPage agePage = new AgeConfirmationPage(driver);
        MainPage mainPage = new MainPage(driver);
        ModelPage modelPage = new ModelPage(driver);
        initDriver(Constant.BASE_URI, driver);

        agePage.confirmIsAdult();
        String modelName = mainPage.moveToFirstModelPage();

        assertEquals(modelName, modelPage.getModelName());
    }

    @TestTemplate
    void testNextModelNavigation(WebDriver driver) {
        AgeConfirmationPage agePage = new AgeConfirmationPage(driver);
        MainPage mainPage = new MainPage(driver);
        ModelPage modelPage = new ModelPage(driver);
        initDriver(Constant.BASE_URI, driver);

        agePage.confirmIsAdult();
        String modelName = mainPage.moveToFirstModelPage();
        modelPage.moveToNextModel();
        modelPage.moveToPreviousModel();

        assertEquals(modelName, modelPage.getModelName());
    }
}
