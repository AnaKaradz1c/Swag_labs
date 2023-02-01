package D_30_Ana_Karadzic_Swag.SwagTests;

import D_30_Ana_Karadzic_Swag.SwagBaseTest.SwagBaseTest;
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

public class SwagYourCartTest extends SwagBaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.get(homeURL);
        loginPage = new SwagLoginPage();
        inventoryPage = new SwagInventoryPage();
        yourCartPage = new SwagYourCartPage();
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

    public void addingOneItem () {
        yourCartPage.clickOnTheShoppingCartButton();
        Assert.assertFalse(isDisplayed(yourCartPage.ShoppingCartBadge));
        Assert.assertEquals(yourCartPage.getTitle(), "YOUR CART");
        yourCartPage.clickOnContinueShopping();
        yourCartPage.clickOnAddBackpack();
        Assert.assertTrue(isDisplayed(yourCartPage.ShoppingCartBadge));
        Assert.assertTrue(isDisplayed(yourCartPage.RemoveFromCartBackpack));
        yourCartPage.clickOnTheShoppingCartButton();
        Assert.assertEquals(yourCartPage.InventoryItem.size(), 1);

    }
    public void removeOneItem () {
        yourCartPage.clickOnRemoveFromCartBackpack();
        Assert.assertFalse(isDisplayed(yourCartPage.ShoppingCartBadge));
    }

    public void addingAndRemovingMultipleItems () {

        yourCartPage.clickOnContinueShopping();
        yourCartPage.clickOnAddJacket();
        Assert.assertTrue(isDisplayed(yourCartPage.RemoveFromCartJacket));
        yourCartPage.clickOnTheShoppingCartButton();
        Assert.assertEquals(yourCartPage.InventoryItem.size(), 2);
        yourCartPage.clickOnContinueShopping();
        scrollToElement(yourCartPage.AddToCartOnesie);
        yourCartPage.clickOnAddOnsie();
        Assert.assertTrue(isDisplayed(yourCartPage.RemoveFromCartOnesie));
        yourCartPage.clickOnTheShoppingCartButton();
        Assert.assertEquals(yourCartPage.InventoryItem.size(), 3);
        scrollToElement(yourCartPage.ContinueShopping);
        yourCartPage.clickOnContinueShopping();
        scrollToElement(yourCartPage.AddToCartTShirt);
        yourCartPage.clickOnAddTShirt();
        Assert.assertTrue(isDisplayed(yourCartPage.RemvoeFromCartTShirt));
        scrollToElement(yourCartPage.ShoppingCartButton);
        yourCartPage.clickOnTheShoppingCartButton();
        Assert.assertEquals(yourCartPage.InventoryItem.size(), 4);
        yourCartPage.clickOnRemoveFromCartBackpack();
        Assert.assertEquals(yourCartPage.InventoryItem.size(), 3);
        yourCartPage.clickOnRemoveFromCartJacket();
        Assert.assertEquals(yourCartPage.InventoryItem.size(), 2);
        yourCartPage.clickOnRemoveFromCartOnesie();
        Assert.assertEquals(yourCartPage.InventoryItem.size(), 1);
        yourCartPage.clickOnRemoveFromCartTShirt();
        Assert.assertFalse(isDisplayed(yourCartPage.ShoppingCartBadge));
    }

    @Test (priority = 10)
    public void addingOneItemStandardUser () {
        logIn(excelReader.getStringData("Login", 1, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        removeOneItem();
    }

    @Test (priority = 20)
    public void addingOneItemProblemUser () {
        logIn(excelReader.getStringData("Login", 2, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        removeOneItem();
    }

    @Test (priority = 30)
    public void addingOneItemPerformanceGlitchUser () {
        logIn(excelReader.getStringData("Login", 3, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        removeOneItem();
    }

    @Test (priority = 40)
    public void addingOneItemLockedOutUser () {
        //test ce pasti na log in-u
        logIn(excelReader.getStringData("Login", 4, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        removeOneItem();
    }

    @Test (priority = 50)
    public void addingAndRemovingMultipleItemsStandardUser () {
        logIn(excelReader.getStringData("Login", 1, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        addingAndRemovingMultipleItems();
    }

    @Test (priority = 60)
    public void addingAndRemovingMultipleItemsProblemUser () {
        // test ce pasti kod dodavanja elementa Jacket, nisu klikabilni  AddJacket, AddToCartTShirt, RemoveFromCartBackpack, RemoveFromCartOnesie
        logIn(excelReader.getStringData("Login", 2, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        addingAndRemovingMultipleItems();
    }

    @Test (priority = 70)
    public void addingAndRemovingMultipleItemsPerformanceGlitchUser () {
        logIn(excelReader.getStringData("Login", 3, 0),
                excelReader.getStringData("Login", 1, 1));
        validLoginAssert();
        addingOneItem();
        addingAndRemovingMultipleItems();
    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
