package ru.juli.praktikum.pageoption;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

// Класс домашней главной стораницы
public class HopePage {

    private WebDriver driver;

    // Локаторы
    private By personalAccount = By.xpath(".//p[text()='Личный Кабинет']"); // локатор Личный кабинет
    private By loginAccount = By.xpath(".//button[text()='Войти в аккаунт']");  // локатор кнопки Войти в аккаунт
    private By buns = By.xpath(".//span[text()='Булки']"); // локатор секции Булки
    private By sauces = By.xpath(".//span[text()='Соусы']"); // локатор секции Соусы
    private By toppings = By.xpath(".//span[text()='Начинки']"); // локатор секции Начинка
    private List<By> menuConstructor = List.of(
            By.xpath("//h2[contains(@class, 'text_type_main-medium') and text()='Булки']"),
            By.xpath("//h2[contains(@class, 'text_type_main-medium') and text()='Соусы']"),
            By.xpath("//h2[contains(@class, 'text_type_main-medium') and text()='Начинки']")
        ); // локатор списка ингридиентов конструктора
    private By createOrder = By.xpath(".//button[text()='Оформить заказ']"); // локатор кнопки оформить заказ
    private By burgerIngredients = By.xpath(".//h1[text()='Соберите бургер']"); // Локатора конструктора ингредиентов Собери бургер


    public HopePage(WebDriver driver) {
        this.driver = driver; // Инициализировали в нём поле driver
    }
    // Найти и кликнуть по Личному кабинету
    public void clickPersonalAccount() {
        driver.findElement(personalAccount).click();
    }
    // Найти и кликнуть по Войти в аккаунт
    public void clickLoginAccount() {
        driver.findElement(loginAccount).click();
    }
    public String getCreateOrder() {
        String actualTextButtonCreateOrder= driver.findElement(createOrder).getText();
        return actualTextButtonCreateOrder;
    }
    // Найти и кликнуть по Булки
    public void clickBuns() {
        driver.findElement(buns).click();
    }
    // Найти и кликнуть по Соусы
    public void clickSauces() {
        driver.findElement(sauces).click();
    }
    // Найти и кликнуть по Начинки
    public void clickToppings() {
        driver.findElement(toppings).click();
    }

    // найти заголовок конструктора бургера взять текст
    public String getBurgerIngredients() {
        String actualTextBurgerIngredients = driver.findElement(burgerIngredients).getText();
        return actualTextBurgerIngredients;
    }
    // найти состав конструктора бургера взять текст элементов списка
    public String getMenuConstructor0() {
        String actualTextMenuConstructor;
        actualTextMenuConstructor = driver.findElement(menuConstructor.get(0)).getText();
        return actualTextMenuConstructor;
    }
    public String getMenuConstructor1() {
        String actualTextMenuConstructor;
        actualTextMenuConstructor = driver.findElement(menuConstructor.get(1)).getText();
        return actualTextMenuConstructor;
    }
    public String getMenuConstructor2() {
        String actualTextMenuConstructor;
        actualTextMenuConstructor = driver.findElement(menuConstructor.get(2)).getText();
        return actualTextMenuConstructor;
    }
}
