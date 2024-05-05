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
import ru.juli.praktikum.pageoption.PersonalAccount;
import ru.juli.praktikum.user.UserGenerator;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static ru.juli.praktikum.constants.Url.BASE_URI;
import static ru.juli.praktikum.user.UserSteps.createUser;
import static ru.juli.praktikum.user.UserSteps.deleteUser;

public class TestTransferPersonalAccount {
    private WebDriver driver;
    private User user;
    protected final UserGenerator userGenerator = new UserGenerator();
    private String accessToken;

    @Before
    @Step("ChromeDriver open the browser and creating test data") // Создание тестовых данных
    public void setUp() {
        driver = getWebDriver(false);
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
    @DisplayName("Transfer to personal account") // имя теста Переход в личный кабинет
    @Description("click-through to the Personal account") // описание теста  переход по клику на «Личный кабинет»
    public void transferButtonLoginAccount() {
        HopePage objHomePage = new HopePage(driver);  // объект класса домашней страницы
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
        HopePage objHomePage = new HopePage(driver);  // объект класса домашней страницы
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
        HopePage objHomePage = new HopePage(driver);  // объект класса домашней страницы
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
        HopePage objHomePage = new HopePage(driver);  // объект класса домашней страницы
        ValidatableResponse response = createUser(user);
        response.assertThat().statusCode(200);
        objHomePage.clickPersonalAccount(); // переход по кнопке Личный кабинет
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setLogin(user.getEmail(), user.getPassword());  // Шаг Заполнения формы входа
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        objHomePage.clickPersonalAccount();
        PersonalAccount objPersonalAccount = new PersonalAccount(driver);
        objPersonalAccount.clickButtonExit();
        assertEquals("Войти", objLoginPage.getTextButtonEnter()); // проверяем что открылось следующее окно c текстом Вход
        accessToken = response.extract().body().jsonPath().get("accessToken"); // берем accessToken из ответа
    }
    @After
    public void cleanUp() {
        if (accessToken != null) {
            ValidatableResponse response = deleteUser(accessToken); // удаляем пользователя
            response.assertThat().statusCode(202);
        }
        driver.quit();
    }
}

//Переход в личный кабинет
//Проверь переход по клику на «Личный кабинет».
//Переход из личного кабинета в конструктор
//Проверь переход по клику на «Конструктор» и на логотип Stellar Burgers.
//Выход из аккаунта
//Проверь выход по кнопке «Выйти» в личном кабинете.