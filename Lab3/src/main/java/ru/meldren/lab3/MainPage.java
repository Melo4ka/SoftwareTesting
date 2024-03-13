package ru.meldren.lab3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super("https://www.banki.ru/", driver);
    }

    @FindBy(xpath = "//input[@placeholder = 'От 1 000 рублей']")
    WebElement mainSearchButton;
}
