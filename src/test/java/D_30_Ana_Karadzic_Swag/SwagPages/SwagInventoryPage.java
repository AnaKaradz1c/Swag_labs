package D_30_Ana_Karadzic_Swag.SwagPages;

import D_30_Ana_Karadzic_Swag.SwagBaseTest.SwagBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagInventoryPage extends SwagBaseTest {
    public SwagInventoryPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "react-burger-menu-btn")
    WebElement BurgerMenu;

    @FindBy (id = "react-burger-cross-btn")
    WebElement BurgerClose;

    @FindBy (id = "logout_sidebar_link")
    public WebElement LogOutButton;

    @FindBy (id = "about_sidebar_link")
    public WebElement About;

    @FindBy (id = "inventory_sidebar_link")
    public WebElement AllItems;

    @FindBy (id = "reset_sidebar_link")
    public WebElement ResetAppState;

    @FindBy (css = "span[class = 'title']")
    public WebElement Title;

    //-------------------------------------

    public void clickOnBurgerIcon () {
        BurgerMenu.click();
    }

    public void clickOnLogOutButton () {
        LogOutButton.click();
    }



    public void clickOnCloseBurgerMenu () {
        BurgerClose.click();
    }
    public String getTitle () {
        return Title.getText();
    }





}
