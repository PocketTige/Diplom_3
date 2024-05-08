package ru.juli.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.juli.praktikum.pageoption.HomePage;
import ru.juli.praktikum.pageoption.LoginPage;
import ru.juli.praktikum.pageoption.PersonalAccount;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static ru.juli.praktikum.user.UserSteps.createUser;

public class TestTransferPersonalAccount extends BaseClassTest {

    @Test
    @DisplayName("Transfer to personal account") // имя теста Переход в личный кабинет
    @Description("click-through to the Personal account") // описание теста  переход по клику на «Личный кабинет»
    public void transferButtonLoginAccount() {
        HomePage objHomePage = new HomePage(driver);  // объект класса домашней страницы
        ValidatableResponse response = createUser(user);
        response.assertThat().statusCode(200);
        objHomePage.clickPersonalAccount(); // переход по кнопке Личный кабинет
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setLogin(user.getEmail(), user.getPassword());  // Шаг Заполнения формы входа
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        objHomePage.clickPersonalAccount();
        PersonalAccount objPersonalAccount = new PersonalAccount(driver);
        assertEquals("Выход", objPersonalAccount.getTextButtonExit());
        accessToken = response.extract().body().jsonPath().get("accessToken"); // берем accessToken из ответа
    }
    @Test
    @DisplayName("Transfer from personal account") // имя теста Переход из личного кабинета
    @Description("Switching from personal account to the constructor") // описание теста  Переход из личного кабинета в конструктор
    public void transferToBurgerConstructor() {
        HomePage objHomePage = new HomePage(driver);  // объект класса домашней страницы
        ValidatableResponse response = createUser(user);
        response.assertThat().statusCode(200);
        objHomePage.clickPersonalAccount(); // переход по кнопке Личный кабинет
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setLogin(user.getEmail(), user.getPassword());  // Шаг Заполнения формы входа
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        objHomePage.clickPersonalAccount();
        PersonalAccount objPersonalAccount = new PersonalAccount(driver);
        objPersonalAccount.clickConstructor();
        assertEquals("Соберите бургер", objHomePage.getBurgerIngredients());
        accessToken = response.extract().body().jsonPath().get("accessToken"); // берем accessToken из ответа
    }
    @Test
    @DisplayName("Transfer from personal account") // имя теста Переход из личного кабинета
    @Description("Switching from personal account to LogoStellarBurgers") // описание теста  Переход из личного кабинета по клику на логотип Stellar Burgers
    public void transferToLogoStellarBurgers() {
        HomePage objHomePage = new HomePage(driver);  // объект класса домашней страницы
        ValidatableResponse response = createUser(user);
        response.assertThat().statusCode(200);
        objHomePage.clickPersonalAccount(); // переход по кнопке Личный кабинет
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setLogin(user.getEmail(), user.getPassword());  // Шаг Заполнения формы входа
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        objHomePage.clickPersonalAccount();
        PersonalAccount objPersonalAccount = new PersonalAccount(driver);
        objPersonalAccount.clickLogoStellarBurgers();
        assertEquals("Соберите бургер", objHomePage.getBurgerIngredients());
        accessToken = response.extract().body().jsonPath().get("accessToken"); // берем accessToken из ответа
    }
    @Test
    @DisplayName("Log out personal account") // имя теста выход по кнопке «Выйти» в личном кабинете
    @Description("Log out from personal account") // описание теста  выход по кнопке «Выйти» в личном кабинете
    public void LogOutPersonalAccount() {
        HomePage objHomePage = new HomePage(driver);  // объект класса домашней страницы
        ValidatableResponse response = createUser(user);
        response.assertThat().statusCode(200);
        objHomePage.clickPersonalAccount(); // переход по кнопке Личный кабинет
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setLogin(user.getEmail(), user.getPassword());  // Шаг Заполнения формы входа
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        objHomePage.clickPersonalAccount();
        PersonalAccount objPersonalAccount = new PersonalAccount(driver);
        objPersonalAccount.clickButtonExit();
        assertEquals("Войти", objLoginPage.getTextButtonEnter()); // проверяем что открылось следующее окно c текстом Вход
        accessToken = response.extract().body().jsonPath().get("accessToken"); // берем accessToken из ответа
    }
}
