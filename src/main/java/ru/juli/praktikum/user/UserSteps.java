package ru.juli.praktikum.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.juli.praktikum.client.User;
import ru.juli.praktikum.client.StellarBurgersClientImpl;

import static ru.juli.praktikum.constants.Url.REQUEST_SPECIFICATION;
import static ru.juli.praktikum.constants.Url.RESPONSE_SPECIFICATION;

public class UserSteps {
    static StellarBurgersClientImpl client = new StellarBurgersClientImpl(REQUEST_SPECIFICATION, RESPONSE_SPECIFICATION);

    @Step("Create user") // создание ользователя
    public static ValidatableResponse createUser(User user) {
        return client.createUser(user);
    }

    @Step("Login user") // логин пользователя
    public static ValidatableResponse loginUser(String email, String password) {
        return client.loginUser(email, password);
    }

    @Step("Delete user") // удаление пользователя
    public static ValidatableResponse deleteUser(String accessToken) {
        return client.deleteUser(accessToken);
    }

//    @After
//    @Step("удаление данных пользователя") // удаление данных пользователя
//    public void cleanUp() {
//        if (accessToken != null) {
//            client.deleteUser(String.valueOf(accessToken)); // удаляем созданного пользователя
//        }
//    }

}
