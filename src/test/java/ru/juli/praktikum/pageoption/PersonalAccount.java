package ru.juli.praktikum.pageoption;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccount {

    private WebDriver driver;
    private By buttonExit = By.className("Account_button__14Yp3");  // локатор кнопки Выход
    private By buttonConstructor = By.className("AppHeader_header__linkText__3q_va");  // локатор кнопки Конструктор
    private By logoStellarBurgers = By.className("AppHeader_header__logo__2D0X2");  // локатор логотипа


    public PersonalAccount(WebDriver driver) {
        this.driver = driver;
    }

    // Найти и взять текст Выход
    public String getTextButtonExit() {
        String actualTextButtonExit = driver.findElement(buttonExit).getText();
        return actualTextButtonExit;
    }
    public void clickButtonExit() {
        driver.findElement(buttonExit).click();
    }
    public void clickConstructor() {
        driver.findElement(buttonConstructor).click();
    }
    public void clickLogoStellarBurgers() {
        driver.findElement(logoStellarBurgers).click();
    }
}
