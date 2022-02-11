package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleCalculator {
    protected WebDriver driver;
    WebElement dataEntryAndResult;
    String historyString;
    String enteredData;

    public GoogleCalculator(WebDriver driver) {
        this.driver = driver;
        this.dataEntryAndResult = driver.findElement(By.xpath("//div[@class='card-section']//div[@role='presentation']"));
    }

    public WebElement getDataEntryAndResult() {
        return dataEntryAndResult;
    }
    public String getHistoryString() {
        return historyString;
    }
    public String getEnteredData() {
        return enteredData + " =";
    }

    public void calculator(String keyFind) {
        dataEntryAndResult.sendKeys(keyFind);
        enteredData = dataEntryAndResult.getText();
        dataEntryAndResult.sendKeys(Keys.ENTER);
        historyString = driver.findElement(By.xpath("//div[@class='card-section']//span[contains(text(),'"
                + enteredData + "')" + "or contains(text(),'"
                + enteredData.replaceAll("[^\\da-zA-Zа-яёА-ЯЁ ]", "") + "')]")).getText();
    }
}
