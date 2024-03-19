package ru.meldren.lab3.bongacams;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.meldren.lab3.Page;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainPage extends Page {

    /* Login form */

    @FindBy(xpath = "//a[@href='/login']")
    WebElement loginButton;

    @FindBy(xpath = "//div[@class='login_popup']/descendant::input")
    WebElement usernameInput;

    @FindBy(xpath = "//div[@class='login_popup']/descendant::input[2]")
    WebElement passwordInput;

    @FindBy(xpath = "//div[@class='login_popup']/descendant::button[@type='submit']")
    WebElement authButton;

    /* Logout form */

    @FindBy(xpath = "//div[@class='user itm']")
    @Getter
    WebElement usernameContainer;

    @FindBy(xpath = "//a[@href='/logout']")
    WebElement logoutButton;

    /* Models view */

    @FindBy(xpath = "//div[@id='mls_models']/descendant::img")
    WebElement firstModelButton;

    @FindBy(xpath = "//div[@id='mls_models']/descendant::a[starts-with(@href,'/profile/')]")
    WebElement firstModelNameContainer;

    /* Quick search */

    @FindBy(xpath = "//div[contains(@class,'ht_quick_search')]")
    WebElement quickSearchButton;

    @FindBy(xpath = "//div[contains(@class,'fl_quick_search')]/descendant::input[@value='female']")
    WebElement quickSearchWomenCategoryButton;

    @FindBy(xpath = "//div[contains(@class,'fl_quick_search')]/descendant::input[@value='transsexual']")
    WebElement quickSearchTransCategoryButton;

    @FindBy(xpath = "//div[contains(@class,'fl_quick_search')]/descendant::input[@value='male']")
    WebElement quickSearchMenCategoryButton;

    @FindBy(xpath = "//div[contains(@class,'fl_quick_search')]/descendant::button[text()='Поиск'")
    WebElement quickSearchProcessButton;

    @FindBy(xpath = "//div[@class='listing_search_result']")
    @Getter
    WebElement quickSearchResult;

    /* Subscriptions */

    @FindBy(xpath = "//a[starts-with(@href,'/followers')]")
    WebElement subscriptionsButton;

    @FindBy(xpath = "//a[@href='/free-tokens/refer-a-friend']")
    WebElement inviteFriendButton;

    @FindBy(xpath = "//div[contains(@class,'ap_form')]/descendant::input")
    WebElement friendEmailInput;

    @FindBy(xpath = "//div[contains(@class,'ap_form')]/descendant::button[@type='submit']")
    WebElement sendFriendInvitationButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        loginButton.click();
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        authButton.click();
    }

    public void logout() {
        new Actions(driver)
                .moveToElement(usernameContainer)
                .moveToElement(logoutButton)
                .click()
                .perform();
    }

    public String getUsername() {
        return usernameContainer.getText();
    }

    public String moveToFirstModelPage() {
        String name = firstModelNameContainer.getText();
        firstModelButton.click();
        return name;
    }

    public void processQuickSearch(GenderCategory genderCategory) {
        quickSearchButton.click();
        WebElement genderButton = switch (genderCategory) {
            case MEN -> quickSearchMenCategoryButton;
            case WOMEN -> quickSearchWomenCategoryButton;
            case TRANS -> quickSearchTransCategoryButton;
        };
        genderButton.click();
        quickSearchProcessButton.click();
    }

    public void inviteFriend(String email) {
        subscriptionsButton.click();
        inviteFriendButton.click();
        friendEmailInput.sendKeys(email);
        sendFriendInvitationButton.click();
    }

    public boolean isFriendInvitationSent() {
        return !driver.findElements(By.xpath("//div[contains(@class,'ap_action_done')]")).isEmpty();
    }

    public enum GenderCategory {

        MEN,
        WOMEN,
        TRANS
    }
}
