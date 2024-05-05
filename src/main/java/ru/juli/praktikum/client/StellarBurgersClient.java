package ru.juli.praktikum.client;

import io.restassured.response.ValidatableResponse;

import java.util.List;

public interface StellarBurgersClient {
    ValidatableResponse createUser(User user);
    ValidatableResponse loginUser(String email, String password);
    ValidatableResponse deleteUser(String accessToken);
}