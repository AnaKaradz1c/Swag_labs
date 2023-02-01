package D_30_Ana_Karadzic_Swag.SwagPages;

import D_30_Ana_Karadzic_Swag.SwagBaseTest.SwagBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagDropdownPage extends SwagBaseTest {
    public SwagDropdownPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy (className = "product_sort_container")
    public WebElement dropdownList;


    @FindBy(css = "span[class = 'active_option']")
    public WebElement Placeholder;

    //----------------------------------------------

    public String getPlaceholderText () {
        return Placeholder.getText();
    }

    public void clickOnDropdownList () {
        dropdownList.click();
    }
}
