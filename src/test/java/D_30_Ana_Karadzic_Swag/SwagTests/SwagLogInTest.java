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

public class SwagLogInTest extends SwagBaseTest {

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

    public void validLoginAssert() {
        String expextedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expextedUrl);
        Assert.assertFalse(isDisplayed(loginPage.LoginButton));
        Assert.assertEquals(inventoryPage.getTitle(), "PRODUCTS");
    }

    public void invalidLoginAssert () {
        String URLexpected = "https://www.saucedemo.com/";
        Assert.assertEquals(driver.getCurrentUrl(), URLexpected);
        String expectedNotification = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPage.getTextErrorMessage(), expectedNotification);
        Assert.assertFalse(isDisplayed(inventoryPage.LogOutButton));
        Assert.assertTrue(isDisplayed(loginPage.LoginButton));
    }
   @Test (priority = 10)
    public void standardUserCanLogIn()  {
        logIn(excelReader.getStringData("Login", 1, 0),
       excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        }

    @Test(priority = 20)
    public void problemUserCanLogIn()  {
        logIn(excelReader.getStringData("Login", 2, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        }

    @Test (priority = 30)
    public void performanceGlitchUserCanLogIn()  {
        logIn(excelReader.getStringData("Login", 3, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        }

    @Test (priority = 40)
    //test ce pasti
    public void LockedOutUserCanLogIn()  {
        logIn(excelReader.getStringData("Login", 4, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
    }

    @Test (priority = 50)
    public void userCannotLogInWithInvalidPassword () {
        for (int i = 1; i<= excelReader.getLastRow("Login"); i++) {
            String username = excelReader.getStringData("Login", 1,0 );
            String password = excelReader.getStringData("Login", i, 3);
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickOnLogInButton();
            invalidLoginAssert();
        }
    }

    @Test (priority = 60)
    public void userCannotLogInWithInvalidUsername () {
        for (int i = 1; i<= excelReader.getLastRow("Login"); i++) {
            String username = excelReader.getStringData("Login", i,2 );
            String password = excelReader.getStringData("Login", 1, 1);
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickOnLogInButton();
            invalidLoginAssert();
        }
    }

    @Test (priority = 70)
    public void userCannotLogInWithInvalidUsernameAndInvalidPassword () {
        for (int i = 1; i<= excelReader.getLastRow("Login"); i++) {
            String username = excelReader.getStringData("Login", i,2 );
            String password = excelReader.getStringData("Login", i, 3);
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickOnLogInButton();
            invalidLoginAssert();
        }
    }

    @Test (priority = 80)
    public void userCannotLoginWithBlankUsername () {
            String validPassword = excelReader.getStringData("Login", 1, 1);
            loginPage.enterBlankUsername();
            loginPage.enterPassword(validPassword);
            loginPage.clickOnLogInButton();
            String notificationExpected = "Epic sadface: Username is required";
            Assert.assertEquals(loginPage.getTextErrorMessage(), notificationExpected);
        }


    @Test (priority = 90)
    public void userCannotLoginWithBlankPassword () {
        for (int i =1; i<= excelReader.getLastRow("Login"); i++) {
            String validUsername = excelReader.getStringData("Login", i, 0);
            loginPage.enterUsername(validUsername);
            loginPage.enterBlankPassword();
            loginPage.clickOnLogInButton();
            String notificationExpected = "Epic sadface: Password is required";
            Assert.assertEquals(loginPage.getTextErrorMessage(), notificationExpected);
        }
    }

    @Test (priority = 100)
    public void userCannotLoginWithBlankUsernameAndBlankPassword () {
        loginPage.enterBlankUsername();
        loginPage.enterBlankPassword();
        loginPage.clickOnLogInButton();
        String notificationExpected = "Epic sadface: Username is required";
        Assert.assertEquals(loginPage.getTextErrorMessage(), notificationExpected);
    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }



}
