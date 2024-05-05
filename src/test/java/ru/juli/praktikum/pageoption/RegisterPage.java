package ru.juli.praktikum.pageoption;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс стораницы регистрации
public class RegisterPage {

    private WebDriver driver;
    private By fieldName = By.xpath(".//fieldset[1]/div/div/input"); // локатор поле имя
    private By fieldEmail = By.xpath(".//fieldset[2]/div/div/input"); // локатор поле Email
    private By fieldPassword = By.xpath(".//fieldset[3]/div/div/input"); // локатор поле пароль
    private By buttonRegister = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Зарегистрироваться']"); // локатор кнопки регистрация
    private By errorPassword = By.xpath(".//fieldset[3]/div/p"); // локатор надписи с ошибкой
    private By loginButton =   By.xpath( ".//a[text()='Войти']"); //локатор кнопки войти
    public RegisterPage(WebDriver driver) {
        this.driver = driver; // Инициализировали в нём поле driver
    }

    ;

    // заполняем поле имя
    public void fillName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    public void fillEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    public void fillPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    public void clickButtonRegister() {
        driver.findElement(buttonRegister).click();
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getTextErrorPassword() {
        String actualTextError = driver.findElement(errorPassword).getText();
        return actualTextError;
    }
    // шаг заполнения формы регистрации
    public void setRegister(String name, String email, String password) {
        fillName(name);
        fillEmail(email);
        fillPassword(password);
        clickButtonRegister();
    }
}
