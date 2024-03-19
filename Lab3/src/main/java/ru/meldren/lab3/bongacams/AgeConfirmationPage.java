package ru.meldren.lab3.bongacams;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.meldren.lab3.Page;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgeConfirmationPage extends Page {

    @FindBy(xpath = "//div[@class='popup_18_plus']")
    @Getter
    WebElement ageConfirmationPopup;

    @FindBy(xpath = "//div[@class='popup_18_plus']/descendant::a[@href='/continue-18-plus']")
    WebElement continueButton;

    @FindBy(xpath = "//div[@class='popup_18_plus']/descendant::a[@href='//google.com/']")
    WebElement leaveButton;

    public AgeConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public void confirmIsAdult() {
        continueButton.click();
    }

    public void confirmIsUnderAge() {
        leaveButton.click();
    }
}
