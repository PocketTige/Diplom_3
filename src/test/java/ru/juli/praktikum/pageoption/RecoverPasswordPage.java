package ru.juli.praktikum.pageoption;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage {
    private WebDriver driver;
    private By buttonEnterRecoverPasswordPage = By.className("Auth_link__1fOlj");  // локатор кнопки Войти

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    // Найти и кликнуть по Войти
    public void clickEnterRecoverPasswordPage() {
        driver.findElement(buttonEnterRecoverPasswordPage).click();
    }
}
