package D_30_Ana_Karadzic_Swag.SwagTests;

import D_30_Ana_Karadzic_Swag.SwagBaseTest.SwagBaseTest;
import D_30_Ana_Karadzic_Swag.SwagPages.SwagInventoryPage;
import D_30_Ana_Karadzic_Swag.SwagPages.SwagLoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SwagLogoutTest extends SwagBaseTest {

   @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.get(homeURL);
        loginPage = new SwagLoginPage();
        inventoryPage = new SwagInventoryPage();
    }
    public void logIn(String username, String password) {

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLogInButton();
    }
    public void logOut () {
        inventoryPage.clickOnBurgerIcon();
        waitForClickability(inventoryPage.LogOutButton);
        inventoryPage.clickOnLogOutButton();
    }

    public void logOutAssert () {
        String urlExpected = "https://www.saucedemo.com/";
        Assert.assertEquals(driver.getCurrentUrl(), urlExpected);
        Assert.assertTrue(isDisplayed(loginPage.LoginButton));
        Assert.assertFalse(isDisplayed(inventoryPage.LogOutButton));
    }

    @Test (priority = 10)
    public void standardUserCanLogOut()  {
        logIn(excelReader.getStringData("Login", 1, 0),
                excelReader.getStringData("Login", 1, 1));
        logOut();
        logOutAssert();
    }

    @Test (priority = 20)
    public void problemUserCanLogOut()  {
        logIn(excelReader.getStringData("Login", 2, 0),
                excelReader.getStringData("Login", 1, 1));
        logOut();
        logOutAssert();
    }

    @Test (priority = 30)
    public void performanceGlitchUserCanLogIn()  {
        logIn(excelReader.getStringData("Login", 3, 0),
                excelReader.getStringData("Login", 1, 1));
        logOut();
        logOutAssert();
    }

    @Test (priority = 40)
    //test ce pasti na log in
    public void LockedOutUserCanLogOut()  {
        logIn(excelReader.getStringData("Login", 4, 0),
                excelReader.getStringData("Login", 1, 1));
        logOut();
        logOutAssert();
    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
