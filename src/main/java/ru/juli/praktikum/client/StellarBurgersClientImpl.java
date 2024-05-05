package ru.juli.praktikum.client;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.juli.praktikum.constants.Url.*;

public class StellarBurgersClientImpl implements StellarBurgersClient {
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public StellarBurgersClientImpl(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        this.requestSpecification = requestSpecification;
        this.responseSpecification = responseSpecification;
    }

    @Override
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(requestSpecification)
                .body(user)
                .post(CREATE_USER)
                .then()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse loginUser(String email, String password) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);
        return given()
                .spec(requestSpecification)
                .when()
                .body(requestBody)
                .post(LOGIN_USER)
                .then()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(requestSpecification)
                .and()
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_USER)
                .then()
                .spec(responseSpecification);
    }

}