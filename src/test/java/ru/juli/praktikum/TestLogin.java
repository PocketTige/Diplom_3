package ru.juli.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.juli.praktikum.client.StellarBurgersClientImpl;
import ru.juli.praktikum.client.User;
import ru.juli.praktikum.pageoption.HopePage;
import ru.juli.praktikum.pageoption.LoginPage;
import ru.juli.praktikum.pageoption.RegisterPage;
import ru.juli.praktikum.user.UserGenerator;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static ru.juli.praktikum.constants.Url.*;
import static ru.juli.praktikum.user.UserSteps.*;

public class TestLogin {
    private WebDriver driver;
    private User user;
    private StellarBurgersClientImpl client = new StellarBurgersClientImpl(REQUEST_SPECIFICATION, RESPONSE_SPECIFICATION);
    protected final UserGenerator userGenerator = new UserGenerator();
    private String accessToken;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();   // Создание экземпляра ChromeDriver
        driver.get(BASE_URI);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


    @Test
    public void loginLoginAccount() {
        user = userGenerator.createNewUnicUser();
        HopePage objHomePage = new HopePage(driver);  // объект класса домашней страницы
        ValidatableResponse response = createUser(user);
        response.assertThat().statusCode(200);
        objHomePage.clickLoginAccount(); // переход по кнопке войти в аккаунт

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        LoginPage objLoginPage = new LoginPage(driver);

        objLoginPage.setLogin(user.getEmail(), user.getPassword());  // Шаг Заполнения формы входа
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        assertEquals("Оформить заказ", objHomePage.getCreateOrder()); // проверяем что открылось следующее окно Вход

//        ValidatableResponse response = loginUser(user.getEmail(), user.getPassword()); // огинимся созданным пользователем
        accessToken = response.extract().body().jsonPath().get("accessToken"); // берем accessToken из ответа
//        ValidatableResponse response2 = deleteUser(accessToken); // удаляем пользователя
//        response2.assertThat().statusCode(202);
    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
//            client.deleteUser(String.valueOf(accessToken)); // удаляем созданного пользователя
            driver.quit();
            ValidatableResponse response2 = deleteUser(accessToken); // удаляем пользователя
            response2.assertThat().statusCode(202);
        }
    }
}
//Проверь:
//вход по кнопке «Войти в аккаунт» на главной,
//вход через кнопку «Личный кабинет»,
//вход через кнопку в форме регистрации,
//вход через кнопку в форме восстановления пароля.
