package ru.juli.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.juli.praktikum.pageoption.HomePage;
import static org.junit.Assert.assertEquals;

public class TestgetBurgerConstructor extends BaseClassTest{

    @Test
    @DisplayName("transitions to the Sauces sections") // имя теста переходы к разделам  Соусы
    @Description("Check that the transitions to the Sauces sections work") // описание теста  Проверить, что работают переходы к разделам  Соусы
    public void transitionsSauces() {
        HomePage objHomePage = new HomePage(driver);  // объект класса домашней страницы
        objHomePage.clickSauces();
        assertEquals("Соусы", objHomePage.getMenuConstructor()); // проверяем что открылось в меню Соусы
            }
    @Test
    @DisplayName("transitions to the Buns sections") // имя теста переходы к разделам  Булки
    @Description("Check that the transitions to the Buns sections work") // описание теста  Проверить, что работают переходы к разделам  Булки
    public void transitionsBuns() {
        HomePage objHomePage = new HomePage(driver);  // объект класса домашней страницы
        objHomePage.clickSauces();
        objHomePage.clickToppings();
        objHomePage.clickBuns();
        assertEquals("Булки", objHomePage.getMenuConstructor()); // проверяем что открылось в меню Булки
    }
    @Test
    @DisplayName("transitions to Toppings sections") // имя теста переходы к разделам  Начинки
    @Description("Check that the transitions to the Toppings sections work") // описание теста  Проверить, что работают переходы к разделам  Начинки
    public void transitionsToppings() {
        HomePage objHomePage = new HomePage(driver);  // объект класса домашней страницы
        objHomePage.clickToppings();
        assertEquals("Начинки", objHomePage.getMenuConstructor()); // проверяем что открылось в меню Начинки
    }

}