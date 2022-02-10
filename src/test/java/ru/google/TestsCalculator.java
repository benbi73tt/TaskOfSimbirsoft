package ru.google;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.GoogleCalculator;
import pages.HomeGooglePage;

public class TestsCalculator extends MainPageTest {


//    @Test
//    public void testWithIntegers() {
//        HomeGooglePage homeGooglePage = new HomeGooglePage(driver, "Калькулятор");
//    }

    @Feature("Проверка результатов")
//    @DisplayName("Вычисления результатов на калькуляторе")
    @DisplayName("Вычисление целых чисел на калькуляторе")
    @ParameterizedTest(name = "{displayName} {arguments}")
//    @CsvSource({"(1+2)*3-40/5,1", "6/0,Infinity", "sin,Error"})
    @CsvSource({"(1+2)*3-40/5,1"})
    public void testWithIntegersSearch(String expression, String result) {
        HomeGooglePage homeGooglePage = new HomeGooglePage(driver);
        homeGooglePage.findAndPressButton("Калькулятор");

        GoogleCalculator googleCalculator = new GoogleCalculator(driver);
        googleCalculator.calculator(expression);

        Assertions.assertEquals(googleCalculator.getHistoryString(), googleCalculator.getEnteredData(),
                "Данные из истории и введенных данных не совпадают");
        Assertions.assertEquals(result, googleCalculator.getDataEntryAndResult().getText(),
                "Ожидаемый результат и результат калькулятора не совпадают");
    }

    @Feature("Проверка результатов")
    @DisplayName("Проверка деления на ноль")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"6/0,Infinity"})
    public void testDivisionByZero(String expression, String result) {
        HomeGooglePage homeGooglePage = new HomeGooglePage(driver);
        homeGooglePage.findAndPressButton("Калькулятор");

        GoogleCalculator googleCalculator = new GoogleCalculator(driver);
        googleCalculator.calculator(expression);

        Assertions.assertEquals(googleCalculator.getHistoryString(), googleCalculator.getEnteredData(),
                "Данные из истории и введенных данных не совпадают");
        Assertions.assertEquals(result, googleCalculator.getDataEntryAndResult().getText(),
                "Ожидаемый результат и результат калькулятора не совпадают");
    }

    @Feature("Проверка результатов")
    @DisplayName("Проверка ошибки при остутствии значения")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"sin,Error"})
    public void testForMissingValue(String expression, String result) {
        HomeGooglePage homeGooglePage = new HomeGooglePage(driver);
        homeGooglePage.findAndPressButton("Калькулятор");

        GoogleCalculator googleCalculator = new GoogleCalculator(driver);
        googleCalculator.calculator(expression);

        Assertions.assertEquals(googleCalculator.getHistoryString(), googleCalculator.getEnteredData(),
                "Данные из истории и введенных данных не совпадают");
        Assertions.assertEquals(result, googleCalculator.getDataEntryAndResult().getText(),
                "Ожидаемый результат и результат калькулятора не совпадают");
    }
}
