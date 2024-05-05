package ru.juli.praktikum.user;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import ru.juli.praktikum.client.User;

public class UserGenerator {
    static Faker faker = new Faker();

    @Step("Создание нового пользователя с рандомными данными") // Создание нового пользователя с рандомными данными
    public User createNewUnicUser() {
        String login = faker.internet().emailAddress(); // Генерация email
        String password = faker.internet().password(6, 8, true, true); // Генерация пароля
        String firstName = faker.name().username(); // Генерация имени пользователя
        return new User(login, password, firstName);
    }
    @Step("Создание нового пользователя с паролем менее 6 символов") // Создание нового пользователя
    public User createNewUserErrorPassword() {
        String login = faker.internet().emailAddress(); // Генерация email
        String password = faker.internet().password(4, 5, true, true); // Генерация пароля
        String firstName = faker.name().username(); // Генерация имени пользователя
        return new User(login, password, firstName);
    }
}