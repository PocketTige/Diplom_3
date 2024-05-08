package ru.juli.praktikum;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.juli.praktikum.pageoption.HomePage;
import ru.juli.praktikum.pageoption.LoginPage;
import ru.juli.praktikum.pageoption.RegisterPage;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static ru.juli.praktikum.user.UserSteps.loginUser;

public class TestRegister extends BaseClassTest{

    @Test
    @DisplayName("success Register") // имя теста Успешная регистрацию
    @Description("Check that registration is working") // описание теста  Проверить, что работает регистрация
    public void successRegister() {
        user = userGenerator.createNewUnicUser();
        HomePage objHomePage = new HomePage(driver);  // объект класса домашней страницы
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
        HomePage objHomePage = new HomePage(driver);
        user = userGenerator.createNewUserErrorPassword();
        objHomePage.clickPersonalAccount();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.clickRegister();
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.setRegister(user.getName(), user.getEmail(), user.getPassword());  // Шаг Заполнения формы
        assertEquals("Некорректный пароль", objRegisterPage.getTextErrorPassword());

    }

}
