package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomeGooglePage {

    WebElement searchField;
    WebElement searchButtonInJSController;
    private WebDriver driver;

    public HomeGooglePage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://google.com");
        this.searchField = driver.findElement(By.xpath("//input[@name='q' and @title='Поиск']"));
        this.searchButtonInJSController = driver.findElement(By.xpath("//div[@class='lJ9FBc']//input[@type='submit' and @value='Поиск в Google']"));
    }

    public HomeGooglePage(WebDriver driver, String search) {
        this.driver = driver;
        this.driver.get("http://google.com/search?q=" + search);
    }

    public void findAndPressButton(String keyFind) {
        searchField.click();
        searchField.sendKeys(keyFind);
        searchButtonInJSController.click();
    }

    public void findAndPressEnter(String keyFind) {
        searchField.click();
        searchField.sendKeys(keyFind, Keys.ENTER);
    }

    public boolean goPage(String namePage){
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (String tab : tabs){
            driver.switchTo().window(tab);
            if(driver.getTitle().contains(namePage))
                return true;
        }
        Assertions.fail("Не удалось открыть вкладку, содержащую " + namePage);
        return false;
    }
}
