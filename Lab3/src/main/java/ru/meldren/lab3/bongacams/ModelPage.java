package ru.meldren.lab3.bongacams;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.meldren.lab3.Page;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelPage extends Page {

    @FindBy(xpath = "//*[@id='bChatRoomTitle']")
    WebElement modelNameContainer;

    @FindBy(xpath = "//*[@id='bCamControlsPrevRoom']")
    WebElement previousModelButton;

    @FindBy(xpath = "//*[@id='bCamControlsNextRoom']")
    WebElement nextModelButton;

    public ModelPage(WebDriver driver) {
        super(driver);
    }

    public String getModelName() {
        return modelNameContainer.getText();
    }

    public void moveToPreviousModel() {
        previousModelButton.click();
    }

    public void moveToNextModel() {
        nextModelButton.click();
    }
}
