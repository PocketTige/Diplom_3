package ru.juli.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.juli.praktikum.pageoption.HomePage;
import ru.juli.praktikum.pageoption.LoginPage;
import ru.juli.praktikum.pageoption.RecoverPasswordPage;
import ru.juli.praktikum.pageoption.RegisterPage;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static ru.juli.praktikum.user.UserSteps.*;

public class TestLogin extends BaseClassTest {

    @Test
    @DisplayName("log in using the Log in to account") // имя теста вход по кнопке «Войти в аккаунт» на главной
    @Description("Check that the login is working using the Log in to account button on the main page") // описание теста  Проверить, что работает вход по кнопке «Войти в аккаунт» на главной
    public void loginButtonLoginAccount() {
        HomePage objHomePage = new HomePage(driver);  // объект класса домашней страницы
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
        HomePage objHomePage = new HomePage(driver);  // объект класса домашней страницы
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
        HomePage objHomePage = new HomePage(driver);  // объект класса домашней страницы
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
        HomePage objHomePage = new HomePage(driver);  // объект класса домашней страницы
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

}