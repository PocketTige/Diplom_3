package ru.juli.praktikum.pageoption;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccaunt {

    // //*[@id="root"]/div/main/div/nav/ul/li[3]/button <button type="button" class="Account_button__14Yp3 text text_type_main-medium text_color_inactive">Выход</button>
    //<p class="AppHeader_header__linkText__3q_va ml-2">Конструктор</p>
    private WebDriver driver;
    private By buttonExit = By.className("Account_button__14Yp3");  // локатор кнопки Выход
    private By buttonConstructor = By.className("AppHeader_header__linkText__3q_va");  // локатор кнопки Конструктор


    public PersonalAccaunt(WebDriver driver) {
        this.driver = driver; // Инициализировали в нём поле driver
    }

    // Найти и взять текст Выход
    public String getTextButtonExit() {
        String actualTextButtonExit = driver.findElement(buttonExit).getText();
        return actualTextButtonExit;
    }
    public void clickConstructor() {
        driver.findElement(buttonConstructor).click();
    }
}
