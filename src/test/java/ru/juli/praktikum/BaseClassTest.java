package ru.juli.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.juli.praktikum.client.User;
import ru.juli.praktikum.user.UserGenerator;
import java.util.concurrent.TimeUnit;
import static ru.juli.praktikum.constants.Url.*;
import static ru.juli.praktikum.user.UserSteps.deleteUser;

public class BaseClassTest {
    WebDriver driver;
    User user;
    protected UserGenerator userGenerator = new UserGenerator();
    String accessToken;

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
            System.setProperty("webdriver.chrome.driver", SYSTEM_YA_BROWSER_PATH);
        } else {
            System.setProperty("webdriver.chrome.driver", SYSTEM_CH_BROWSER_PATH);
        }
        return new ChromeDriver();
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
