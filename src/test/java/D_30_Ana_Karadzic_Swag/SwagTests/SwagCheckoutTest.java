package D_30_Ana_Karadzic_Swag.SwagTests;

import D_30_Ana_Karadzic_Swag.SwagBaseTest.SwagBaseTest;
import D_30_Ana_Karadzic_Swag.SwagPages.SwagCheckoutPage;
import D_30_Ana_Karadzic_Swag.SwagPages.SwagInventoryPage;
import D_30_Ana_Karadzic_Swag.SwagPages.SwagLoginPage;
import D_30_Ana_Karadzic_Swag.SwagPages.SwagYourCartPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SwagCheckoutTest extends SwagBaseTest {


    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.get(homeURL);
        loginPage = new SwagLoginPage();
        inventoryPage = new SwagInventoryPage();
        yourCartPage = new SwagYourCartPage();
        checkoutPage = new SwagCheckoutPage();
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

    public void validCheckout() {
        yourCartPage.clickOnTheShoppingCartButton();
        Assert.assertFalse(isDisplayed(yourCartPage.ShoppingCartBadge));
        Assert.assertEquals(yourCartPage.getTitle(), "YOUR CART");
        yourCartPage.clickOnContinueShopping();
        yourCartPage.clickOnAddBackpack();
        Assert.assertTrue(isDisplayed(yourCartPage.ShoppingCartBadge));
        Assert.assertTrue(isDisplayed(yourCartPage.RemoveFromCartBackpack));
        yourCartPage.clickOnTheShoppingCartButton();
        Assert.assertEquals(yourCartPage.InventoryItem.size(), 1);
        yourCartPage.clickOnContinueShopping();
        yourCartPage.clickOnAddOnsie();
        yourCartPage.clickOnTheShoppingCartButton();
        Assert.assertEquals(yourCartPage.InventoryItem.size(), 2);
        checkoutPage.clickCheckout();
        Assert.assertEquals(checkoutPage.getTitle(), "CHECKOUT: YOUR INFORMATION");
        Assert.assertFalse(isDisplayed(checkoutPage.CheckoutButton));
        checkoutPage.clickOnCancelButton();
        Assert.assertEquals(checkoutPage.getTitle(), "YOUR CART");
        Assert.assertFalse(isDisplayed(checkoutPage.CancelButton));
        checkoutPage.clickCheckout();
        String validFirstName = "Ana";
        String validLastName = "Karadzic";
        String validPostalCode = "11000";
        String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
        checkoutPage.enterFirstName(validFirstName);
        checkoutPage.enterLastName(validLastName);
        checkoutPage.enterPostalCode(validPostalCode);
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getTitle(), "CHECKOUT: OVERVIEW");
        Assert.assertTrue(isDisplayed(checkoutPage.FinishButton));
        checkoutPage.clickOnFinishButton();
        Assert.assertTrue(isDisplayed(checkoutPage.PonyExpressPhoto));
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }
    public void addingOneItem () {
        yourCartPage.clickOnTheShoppingCartButton();
        Assert.assertFalse(isDisplayed(yourCartPage.ShoppingCartBadge));
        Assert.assertEquals(yourCartPage.getTitle(), "YOUR CART");
        yourCartPage.clickOnContinueShopping();
        yourCartPage.clickOnAddBackpack();
        Assert.assertTrue(isDisplayed(yourCartPage.ShoppingCartBadge));
        Assert.assertTrue(isDisplayed(yourCartPage.RemoveFromCartBackpack));
        yourCartPage.clickOnTheShoppingCartButton();
        checkoutPage.clickCheckout();
        Assert.assertEquals(checkoutPage.getTitle(), "CHECKOUT: YOUR INFORMATION");
        Assert.assertTrue(isDisplayed(checkoutPage.ContinueButton));
    }
    public void checkoutBlankFirstName() {

        String validLastName = "Karadzic";
        String validPostalCode = "11000";
        String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
        checkoutPage.leaveFirstNameBlank();
        checkoutPage.enterLastName(validLastName);
        checkoutPage.enterPostalCode(validPostalCode);
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getFirstNameError(), "Error: First Name is required");
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

    }

    public void checkoutBlankLastName() {

        String validFirstName = "Ana";
        String validPostalCode = "11000";
        String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
        checkoutPage.leaveLastNameBlank();
        checkoutPage.enterFirstName(validFirstName);
        checkoutPage.enterPostalCode(validPostalCode);
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getFirstNameError(), "Error: Last Name is required");
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }

    public void checkoutBlankPostalCode () {

        String validFirstName = "Ana";
        String validLastName = "Karadzic";
        String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
        checkoutPage.leavePostalCodeBlank();
        checkoutPage.enterFirstName(validFirstName);
        checkoutPage.enterLastName(validLastName);
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getFirstNameError(), "Error: Postal Code is required");
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }
    public void checkoutAllBlank() {
        String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
        checkoutPage.leavePostalCodeBlank();
        checkoutPage.leaveFirstNameBlank();
        checkoutPage.leaveLastNameBlank();
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getFirstNameError(), "Error: First Name is required");
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }

    public void checkingOutEmptyCart () {
        yourCartPage.clickOnTheShoppingCartButton();
        checkoutPage.clickCheckout();
        checkoutPage.clickContinue();
        String validFirstName = "Ana";
        String validLastName = "Karadzic";
        String validPostalCode = "11000";
        String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
        checkoutPage.enterFirstName(validFirstName);
        checkoutPage.enterLastName(validLastName);
        checkoutPage.enterPostalCode(validPostalCode);
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        Assert.assertEquals(checkoutPage.getTotalSum(), "Total: $0.00");
        checkoutPage.clickOnFinishButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertFalse(isDisplayed(checkoutPage.PonyExpressPhoto));
    }



    @Test (priority = 10)
    public void validCheckOutItemsStandardUser ()  {
        logIn(excelReader.getStringData("Login", 1, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        validCheckout();

    }

    @Test (priority = 20)
    public void validCheckOutItemsProblemUser ()  {
        // test ce pasti jer u formi za dodavanje podataka, polje "Last Name" prepisuje unos po karakteru u polje "First Name"
        logIn(excelReader.getStringData("Login", 2, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        validCheckout();
    }

    @Test (priority = 30)
    public void validCheckOutItemsPerformanceGlitchUser () {
        logIn(excelReader.getStringData("Login", 3, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        validCheckout();
    }

    @Test (priority = 40)
    public void validCheckOutItemsLockedOutUser () {
        //test ce pasti na log in
        logIn(excelReader.getStringData("Login", 4, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        validCheckout();
    }

    @Test (priority = 50)
    public void standardUserCannotCheckoutWithBlankFirstName () {
        logIn(excelReader.getStringData("Login", 1, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        checkoutBlankFirstName();
    }

    @Test (priority = 60)
    public void problemUserCannotCheckoutWithBlankFirstName () {
        //// test ce pasti jer u formi za dodavanje podataka, polje "Last Name" prepisuje unos po karakteru u polje "First Name"
        logIn(excelReader.getStringData("Login", 2, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        checkoutBlankFirstName();
    }

    @Test (priority = 70)
    public void performanceGlitchUserCannotCheckoutWithBlankFirstName () {
        logIn(excelReader.getStringData("Login", 3, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        checkoutBlankFirstName();
    }

    @Test (priority = 80)
    public void lockedOutUserCannotCheckoutWithBlankFirstName () {
        //test ce pasti na log in
        logIn(excelReader.getStringData("Login", 4, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        checkoutBlankFirstName();
    }

    @Test (priority = 90)
    public void standardUserCannotCheckoutWithBlankLastName () {
        logIn(excelReader.getStringData("Login", 1, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        checkoutBlankLastName();
    }

    @Test (priority = 100)
    public void problemUserCannotCheckoutWithBlankLastName () {
        logIn(excelReader.getStringData("Login", 2, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        checkoutBlankLastName();
    }

    @Test (priority = 110)
    public void performanceGlitchUserCannotCheckoutWithBlankLastName () {
        logIn(excelReader.getStringData("Login", 3, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        checkoutBlankLastName();
    }

    @Test (priority = 120)
    public void lockedOutUserCannotCheckoutWithBlankLastName () {
        // test ce pasti na log in
        logIn(excelReader.getStringData("Login", 4, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        checkoutBlankLastName();
    }

    @Test (priority = 130)
    public void standardUserCannotCheckoutWithBlankPostalCode () {
        logIn(excelReader.getStringData("Login", 1, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        checkoutBlankPostalCode();
    }

    @Test (priority = 140)
    public void standardUserCannotCheckoutWithAllFieldsBlank () {
        logIn(excelReader.getStringData("Login", 1, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        checkoutAllBlank();
    }

    @Test (priority = 150)
    public void problemUserCannotCheckoutWithAllFieldsBlank () {
        logIn(excelReader.getStringData("Login", 2, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        checkoutAllBlank();
    }

    @Test (priority = 160)
    public void performanecGlitchUserCannotCheckoutWithAllFieldsBlank () {
        logIn(excelReader.getStringData("Login", 3, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        checkoutAllBlank();
    }

    @Test (priority = 170)
    public void lockedOutUserCannotCheckoutWithAllFieldsBlank () {
        //test ce pasti na log in
        logIn(excelReader.getStringData("Login", 4, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        checkoutAllBlank();
    }

    @Test (priority = 180)
    public void standardUserCannotCheckoutEmptyCart () {
        //test ce pasti, jer dozvoljava porucivanje bez dodatih stavki
        logIn(excelReader.getStringData("Login", 1, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();

        checkingOutEmptyCart();
    }

    @Test (priority = 190)
    public void problemUserCannotCheckoutEmptyCart () {
       // test ce pasti jer u formi za dodavanje podataka, polje "Last Name" prepisuje unos po karakteru u polje "First Name"
        logIn(excelReader.getStringData("Login", 2, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        checkingOutEmptyCart();
    }

    @Test (priority = 200)
    public void performanceGlitchUserCannotCheckoutEmptyCart () {
        //test ce pasti, jer dozvoljava porucivanje bez dodatih stavki
        logIn(excelReader.getStringData("Login", 3, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        checkingOutEmptyCart();
    }

    @Test (priority = 210)
    public void lockedOutUserCannotCheckoutEmptyCart () {
        //test ce pasti na log in
        logIn(excelReader.getStringData("Login", 4, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        checkingOutEmptyCart();
    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
