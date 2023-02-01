package D_30_Ana_Karadzic_Swag.SwagPages;

import D_30_Ana_Karadzic_Swag.SwagBaseTest.SwagBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SwagYourCartPage extends SwagBaseTest {
    public SwagYourCartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "shopping_cart_container")
    public WebElement ShoppingCartButton;

    @FindBy(css = "span[class = 'shopping_cart_badge']")
    public WebElement ShoppingCartBadge;

    @FindBy (css = "span[class = 'title']")
    public WebElement Title;

    @FindBy (id = "continue-shopping")
    public WebElement ContinueShopping;

    @FindBy ( id = "add-to-cart-sauce-labs-backpack")
    public WebElement AddToCartBackpack;
    @FindBy (id = "remove-sauce-labs-backpack")
    public WebElement RemoveFromCartBackpack;
    @FindBy (id = "add-to-cart-sauce-labs-bolt-t-shirt")
    public WebElement AddToCartTShirt;

    @FindBy (id = "remove-sauce-labs-bolt-t-shirt")
    public WebElement RemvoeFromCartTShirt;

    @FindBy (id = "add-to-cart-sauce-labs-onesie")
    public WebElement AddToCartOnesie;

    @FindBy (id = "remove-sauce-labs-onesie")
    public WebElement RemoveFromCartOnesie;
    @FindBy (id = "add-to-cart-sauce-labs-fleece-jacket")
    public WebElement AddToCartJacket;

    @FindBy (id = "remove-sauce-labs-fleece-jacket")
    public WebElement RemoveFromCartJacket;



    @FindBy (css = "div[class = 'inventory_item_name' ]")
    public List <WebElement> InventoryItem;

    //---------------------------------

    public void clickOnTheShoppingCartButton () {
        ShoppingCartButton.click();
    }

    public String getTitle () {
        return Title.getText();
    }

    public void clickOnContinueShopping() {
        ContinueShopping.click();
    }

    public void clickOnAddBackpack () {
        AddToCartBackpack.click();
    }

    public void clickOnRemoveFromCartBackpack () {
        RemoveFromCartBackpack.click();
    }

    public void clickOnAddTShirt () {
        AddToCartTShirt.click();
    }

    public void clickOnAddOnsie() {
        AddToCartOnesie.click();
    }

    public void clickOnAddJacket () {
        AddToCartJacket.click();
    }

    public void clickOnRemoveFromCartTShirt () {
        RemvoeFromCartTShirt.click();
    }

    public void clickOnRemoveFromCartOnesie () {
        RemoveFromCartOnesie.click();
    }

    public void clickOnRemoveFromCartJacket () {
        RemoveFromCartJacket.click();
    }
}
