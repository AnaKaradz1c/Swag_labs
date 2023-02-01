package D_30_Ana_Karadzic_Swag.SwagPages;

import D_30_Ana_Karadzic_Swag.SwagBaseTest.SwagBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLoginPage extends SwagBaseTest {
    public SwagLoginPage() {

        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "user-name")
   public WebElement Username;

    @FindBy(id = "password")
   public WebElement Password;

    @FindBy (id = "login-button")
  public  WebElement LoginButton;

    @FindBy (css = ".error-message-container.error")
    public WebElement ErrorMessage;



    //------------------------------------------

    public void enterUsername (String username) {
    Username.clear();
    Username.sendKeys(username);
    }

    public void enterPassword (String password) {
        Password.clear();
        Password.sendKeys(password);
    }

    public void clickOnLogInButton () {

        LoginButton.click();
    }

    public String getTextErrorMessage () {
       return ErrorMessage.getText();
    }

    public void enterBlankUsername () {
        Username.clear();
    }

    public void enterBlankPassword () {
        Password.clear();
    }


}
