package ru.juli.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.juli.praktikum.client.User;
import ru.juli.praktikum.pageoption.HopePage;
import ru.juli.praktikum.pageoption.LoginPage;
import ru.juli.praktikum.pageoption.RegisterPage;
import ru.juli.praktikum.user.UserGenerator;
import org.junit.After;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static ru.juli.praktikum.constants.Url.*;
import static ru.juli.praktikum.user.UserSteps.deleteUser;
import static ru.juli.praktikum.user.UserSteps.loginUser;

public class TestRegister {
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
    @DisplayName("success Register") // имя теста Успешная регистрацию
    @Description("Check that registration is working") // описание теста  Проверить, что работает регистрация
    public void successRegister() {
        user = userGenerator.createNewUnicUser();
        HopePage objHomePage = new HopePage(driver);  // объект класса домашней страницы
        objHomePage.clickPersonalAccount();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.clickRegister();
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.setRegister(user.getName(), user.getEmail(), user.getPassword());  // Шаг Заполнения формы регистрации
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertEquals("Войти", objLoginPage.getTextButtonEnter()); // проверяем что открылось следующее окно c текстом Вход

        ValidatableResponse response = loginUser(user.getEmail(), user.getPassword()); // логинимся этим пользователем через Api
        accessToken = response.extract().body().jsonPath().get("accessToken"); // берем accessToken из ответа
    }

    @Test

    @DisplayName("error for an incorrect password") // имя теста Ошибка для некорректного пароля
    @Description("Check that it gives an error for an incorrect password") // описание теста  Проверить, что выдает Ошибку для некорректного пароля. Минимальный пароль — шесть символов.
    public void errorPassword() {
        HopePage objHomePage = new HopePage(driver);
        user = userGenerator.createNewUserErrorPassword();
        objHomePage.clickPersonalAccount();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.clickRegister();
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.setRegister(user.getName(), user.getEmail(), user.getPassword());  // Шаг Заполнения формы
        assertEquals("Некорректный пароль", objRegisterPage.getTextErrorPassword());

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
