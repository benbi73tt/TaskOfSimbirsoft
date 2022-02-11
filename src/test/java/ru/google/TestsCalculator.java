package ru.google;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.GoogleCalculator;
import pages.HomeGooglePage;
import steps.Steps;

public class TestsCalculator extends BaseTest {

    @Feature("Проверка результатов")
    @DisplayName("Вычисление целых чисел на калькуляторе")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"(1+2)*3-40/5,1"})
    public void testWithIntegersSearch(String expression, String result) {
        HomeGooglePage homeGooglePage = new HomeGooglePage(driver);
        homeGooglePage.findAndPressButton("Калькулятор");

        GoogleCalculator googleCalculator = new GoogleCalculator(driver);
        googleCalculator.calculator(expression);

        Steps.checkHistoryAndDataEntry(googleCalculator.getHistoryString(), googleCalculator.getEnteredData());
        Steps.checkResult(result, googleCalculator.getDataEntryAndResult().getText(), driver);
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

        Steps.checkHistoryAndDataEntry(googleCalculator.getHistoryString(), googleCalculator.getEnteredData());
        Steps.checkResult(result, googleCalculator.getDataEntryAndResult().getText(), driver);
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

        Steps.checkHistoryAndDataEntry(googleCalculator.getHistoryString(), googleCalculator.getEnteredData());
        Steps.checkResult(result, googleCalculator.getDataEntryAndResult().getText(), driver);
    }
}
