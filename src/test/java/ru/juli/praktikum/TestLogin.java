package ru.juli.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.juli.praktikum.client.User;
import ru.juli.praktikum.pageoption.HopePage;
import ru.juli.praktikum.pageoption.LoginPage;
import ru.juli.praktikum.pageoption.RecoverPasswordPage;
import ru.juli.praktikum.pageoption.RegisterPage;
import ru.juli.praktikum.user.UserGenerator;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static ru.juli.praktikum.constants.Url.*;
import static ru.juli.praktikum.user.UserSteps.*;

public class TestLogin {
    private WebDriver driver;
    private User user;
    protected final UserGenerator userGenerator = new UserGenerator();
    private String accessToken;

    @Before
    @Step("ChromeDriver open the browser and creating test data") // Создание тестовых данных
    public void setUp() {
        driver = getWebDriver(true);
        user = userGenerator.createNewUnicUser();
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
    @DisplayName("log in using the Log in to account") // имя теста вход по кнопке «Войти в аккаунт» на главной
    @Description("Check that the login is working using the Log in to account button on the main page") // описание теста  Проверить, что работает вход по кнопке «Войти в аккаунт» на главной
    public void loginButtonLoginAccount() {
        HopePage objHomePage = new HopePage(driver);  // объект класса домашней страницы
        ValidatableResponse response = createUser(user);
        response.assertThat().statusCode(200);
        objHomePage.clickLoginAccount(); // переход по кнопке Войти в аккаунт
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setLogin(user.getEmail(), user.getPassword());  // Шаг Заполнения формы входа
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertEquals("Оформить заказ", objHomePage.getCreateOrder()); // проверяем что открылось следующее окно
        accessToken = response.extract().body().jsonPath().get("accessToken"); // берем accessToken из ответа
    }
    @Test
    @DisplayName("log in using the Personal account") // имя теста вход через кнопку «Личный кабинет»
    @Description("Check that the transitions to the Personal account work") // описание теста  Проверить, что работает вход через кнопку «Личный кабинет»
    public void loginButtonPersonalAccount() {
        HopePage objHomePage = new HopePage(driver);  // объект класса домашней страницы
        ValidatableResponse response = createUser(user);
        response.assertThat().statusCode(200);
        objHomePage.clickPersonalAccount(); // переход по кнопке  кнопке Личный кабинет
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setLogin(user.getEmail(), user.getPassword());  // Шаг Заполнения формы входа
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertEquals("Оформить заказ", objHomePage.getCreateOrder()); // проверяем что открылось следующее окно
        accessToken = response.extract().body().jsonPath().get("accessToken"); // берем accessToken из ответа
    }
    @Test
    @DisplayName("log in using the button in the registration form") // имя теста вход через кнопку в форме регистрации
    @Description("Check that the login is working via the button in the registration form") // описание теста  Проверить, что работает вход через кнопку в форме регистрации
    public void loginRegisterPage() {
        HopePage objHomePage = new HopePage(driver);  // объект класса домашней страницы
        ValidatableResponse response = createUser(user);
        response.assertThat().statusCode(200);
        objHomePage.clickLoginAccount(); // переход по кнопке войти в аккаунт
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.clickRegister(); // переход по кнопке зарегистрироваться
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.clickLoginButton();
        objLoginPage.setLogin(user.getEmail(), user.getPassword());  // Шаг Заполнения формы входа
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertEquals("Оформить заказ", objHomePage.getCreateOrder()); // проверяем что открылось следующее окно
        accessToken = response.extract().body().jsonPath().get("accessToken"); // берем accessToken из ответа
    }
    @Test
    @DisplayName("log in using the button in the password recovery form") // имя теста вход через кнопку в форме восстановления пароля
    @Description("Check that the login is working via the button in the password recovery form") // описание теста  Проверить, что работает вход через кнопку в форме восстановления пароля
    public void loginRecoverPassword() {
        HopePage objHomePage = new HopePage(driver);  // объект класса домашней страницы
        ValidatableResponse response = createUser(user);
        response.assertThat().statusCode(200);
        objHomePage.clickLoginAccount(); // переход по кнопке войти в аккаунт
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.clickRecoverPassword(); // переход по кнопке Восстановить пароль
        RecoverPasswordPage objRecoverPasswordPage = new RecoverPasswordPage(driver);
        objRecoverPasswordPage.clickEnterRecoverPasswordPage(); // переход по кнопке Войти
        objLoginPage.setLogin(user.getEmail(), user.getPassword());  // Шаг Заполнения формы входа
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertEquals("Оформить заказ", objHomePage.getCreateOrder()); // проверяем что открылось следующее окно
        accessToken = response.extract().body().jsonPath().get("accessToken"); // берем accessToken из ответа
    }
    @After
    @Step("closing the browser and delete test data") // Закрытие браузера и удаление тестовых данных
    public void cleanUp() {
        if (accessToken != null) {
            ValidatableResponse response = deleteUser(accessToken); // удаляем пользователя
            response.assertThat().statusCode(202);
        }
        driver.quit();
    }
}