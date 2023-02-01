package D_30_Ana_Karadzic_Swag.SwagTests;

import D_30_Ana_Karadzic_Swag.SwagBaseTest.SwagBaseTest;
import D_30_Ana_Karadzic_Swag.SwagPages.SwagDropdownPage;
import D_30_Ana_Karadzic_Swag.SwagPages.SwagInventoryPage;
import D_30_Ana_Karadzic_Swag.SwagPages.SwagLoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SwagDropdownTest extends SwagBaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.get(homeURL);
        loginPage = new SwagLoginPage();
        inventoryPage = new SwagInventoryPage();
        dropdownPage = new SwagDropdownPage();

        }

    public void logIn(String username, String password) {

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLogInButton();
    }

    public void dropdownListItems () {
        Assert.assertEquals(dropdownPage.getPlaceholderText(), "NAME (A TO Z)");
        dropdownPage.clickOnDropdownList();
        selectByValue(dropdownPage.dropdownList, "za");
        Assert.assertEquals(dropdownPage.getPlaceholderText(), "NAME (Z TO A)");
        selectByText(dropdownPage.dropdownList, "Price (low to high)");
        Assert.assertEquals(dropdownPage.getPlaceholderText(), "PRICE (LOW TO HIGH)");
        selectByValue(dropdownPage.dropdownList, "hilo");
        Assert.assertEquals(dropdownPage.getPlaceholderText(), "PRICE (HIGH TO LOW)");
        selectByValue(dropdownPage.dropdownList, "az");
        Assert.assertEquals(dropdownPage.getPlaceholderText(), "NAME (A TO Z)");
    }

    @Test (priority = 10)
    public void dropdownListIsClickableForStandardUser () {
        logIn(excelReader.getStringData("Login", 1, 0),
                excelReader.getStringData("Login", 1, 1));
      dropdownListItems();

    }

    @Test (priority = 20)
    public void dropdownListIsClickableForProblemUser () { // test ce pasti, postoji bug u Dropdown listi, ne selektuje nista osim prve vrednosti
        logIn(excelReader.getStringData("Login", 2, 0),
                excelReader.getStringData("Login", 1, 1));
        dropdownListItems();

    }

    @Test (priority = 30)
    public void dropdownListIsClickableForPerformanceGlitchUser () {
        // test pada u asertaciji Price High to Low, actual result [[ChromeDriver: chrome on WINDOWS (1ff1edb0bc005572c23f2cc2685db886)] -> class name: product_sort_container]
        logIn(excelReader.getStringData("Login", 3, 0),
                excelReader.getStringData("Login", 1, 1));
        dropdownListItems();
    }

    @Test (priority = 40)
    public void dropdownListIsClickableForLockedOutUser () {
        //test pada vec na log in
        logIn(excelReader.getStringData("Login", 4, 0),
                excelReader.getStringData("Login", 1, 1));
        dropdownListItems();

    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
