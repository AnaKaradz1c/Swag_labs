package D_30_Ana_Karadzic_Swag.SwagBaseTest;

import D_30_Ana_Karadzic_Swag.SwagPages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class SwagBaseTest {
    public static WebDriver driver;
    public WebDriverWait wdwait;

    public ExcelReader excelReader;
    public String homeURL;
    public SwagLoginPage loginPage;
    public SwagInventoryPage inventoryPage;
    public SwagDropdownPage dropdownPage;
    public SwagYourCartPage yourCartPage;
    public SwagCheckoutPage checkoutPage;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        excelReader  = new ExcelReader("src/test/java/Swag/Swagg.xlsx");
        homeURL = excelReader.getStringData("URL", 1,0);

    }

    public boolean isDisplayed (WebElement element) {
        boolean webelement = false;
        try {
            webelement = element.isDisplayed();
        } catch (Exception e) {

        }
        return webelement;
    }

    public void waitForVisibility (WebElement element) {
        wdwait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickability (WebElement element) {
        wdwait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void selectByText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }


    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
