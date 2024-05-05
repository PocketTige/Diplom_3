package ru.juli.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.juli.praktikum.pageoption.HopePage;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static ru.juli.praktikum.constants.Url.BASE_URI;

public class TestgetBurgerConstructor {
    private WebDriver driver;
    @Before
    @Step("ChromeDriver open the browser") // Создание тестовых данных
    public void setUp() {
        driver = getWebDriver(true);
        WebDriverManager.chromedriver().setup();   // Создание экземпляра ChromeDriver
        driver.get(BASE_URI);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    ChromeDriver getWebDriver(Boolean useYandexBrowser) {
        if (useYandexBrowser) {
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\WebDriver\\yandexdriver\\yandexdriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\WebDriver\\chromedriver-win64\\chromedriver.exe");
        }
        return new ChromeDriver();
    }
    @Test
    @DisplayName("transitions to the Sauces sections") // имя теста переходы к разделам  Соусы
    @Description("Check that the transitions to the Sauces sections work") // описание теста  Проверить, что работают переходы к разделам  Соусы
    public void transitionsSauces() {
        HopePage objHomePage = new HopePage(driver);  // объект класса домашней страницы
        objHomePage.clickSauces();
        assertEquals("Соусы", objHomePage.getMenuConstructor1()); // проверяем что открылось в меню Соусы
            }
    @Test
    @DisplayName("transitions to the Buns sections") // имя теста переходы к разделам  Булки
    @Description("Check that the transitions to the Buns sections work") // описание теста  Проверить, что работают переходы к разделам  Булки
    public void transitionsBuns() {
        HopePage objHomePage = new HopePage(driver);  // объект класса домашней страницы
        objHomePage.clickSauces();
        objHomePage.clickToppings();
        objHomePage.clickBuns();
        assertEquals("Булки", objHomePage.getMenuConstructor0()); // проверяем что открылось в меню Булки
    }
    @Test
    @DisplayName("transitions to Toppings sections") // имя теста переходы к разделам  Начинки
    @Description("Check that the transitions to the Toppings sections work") // описание теста  Проверить, что работают переходы к разделам  Начинки
    public void transitionsToppings() {
        HopePage objHomePage = new HopePage(driver);  // объект класса домашней страницы
        objHomePage.clickToppings();
        assertEquals("Начинки", objHomePage.getMenuConstructor2()); // проверяем что открылось в меню Начинки
    }

    @After
    @Step("closing the browser") // Закрытие браузера
    public void cleanUp() {
            driver.quit();
        }
}