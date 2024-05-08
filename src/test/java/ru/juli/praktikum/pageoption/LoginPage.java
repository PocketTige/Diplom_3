package ru.juli.praktikum.pageoption;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// Класс стораницы входа в личный кабинет
public class LoginPage {
    private WebDriver driver;

    private By fillLoginEmail = By.xpath(".//input[@name='name']"); //локатор поля Email
    private By fillLoginPassword = By.xpath(".//input[@name='Пароль']"); //локатор поля Пароль
    //".//button[text()='Войти в аккаунт']"
    private By buttonEnter = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Войти']"); //локатор Войти
    private By register = By.xpath("//*[@id='root']/div/main/div/div/p[1]/a"); // локатор кнопки Зарегистрироваться
    private By recoverPassword = By.xpath(".//a[text()='Восстановить пароль']"); // локатор Восстановить пароль
    private By buttonConstructorLoginPage = By.className("AppHeader_header__linkText__3q_va");  // локатор кнопки Конструктор

    // Найти и кликнуть по Личному кабинету
    public LoginPage(WebDriver driver) {
        this.driver = driver; // Инициализировали в нём поле driver
    }
    // Найти и кликнуть по Зарегистрироваться
    public void clickRegister() {
        driver.findElement(register).click();
    }
    // найти и нажать Восстановить пароль
    public void clickRecoverPassword() {
        driver.findElement(recoverPassword).click();
    }

    public String getTextButtonEnter() {
        String actualTextButtonEnter = driver.findElement(buttonEnter).getText();
        return actualTextButtonEnter;
    }

    public void fillLoginEmail(String email) {
        driver.findElement(fillLoginEmail).sendKeys(email);
    }
    public void fillLoginPassword(String password) {
        driver.findElement(fillLoginPassword).sendKeys(password);
    }
    public void clickButtonEnter() {
        driver.findElement(buttonEnter).click();
    }
    public void clickConstructorLoginPage() {
        driver.findElement(buttonConstructorLoginPage).click();
    }

    public void setLogin(String email, String password) {
        fillLoginEmail(email);
        fillLoginPassword(password);
        clickButtonEnter();
    }
}
