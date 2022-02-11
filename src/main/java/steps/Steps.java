package steps;

import helpers.Screenshoter;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class Steps {

    @Step("Проверка совпадения введенных данных и истории : {history} и {dataEntry}")
    public static void checkHistoryAndDataEntry(String history, String dataEntry){
        Assertions.assertEquals(history, dataEntry,
                "Данные из истории и введенных данных не совпадают");
    }

    @Step("Проверка результата вычислений ")
    public static void checkResult(String expectedResult, String foundResult,WebDriver driver){
        Screenshoter.getScreen(driver);
        Assertions.assertEquals(expectedResult, foundResult,
                "Ожидаемый результат и результат калькулятора не совпадают");
    }
}

