package D_30_Ana_Karadzic_Swag.SwagPages;

import D_30_Ana_Karadzic_Swag.SwagBaseTest.SwagBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagCheckoutPage extends SwagBaseTest {
    public SwagCheckoutPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "validCheckout")
    public WebElement CheckoutButton;

    @FindBy (css = "span[class = 'title']")
    public WebElement Title;

    @FindBy (id = "cancel")
    public WebElement CancelButton;

    @FindBy (id = "first-name")
    public WebElement FirstNameField;

    @FindBy (id = "last-name")
    public WebElement LastNameField;

    @FindBy (id = "postal-code")
    public WebElement PostalCode;

    @FindBy (id = "continue")
    public WebElement ContinueButton;

    @FindBy (id = "finish")
    public WebElement FinishButton;

    @FindBy (className = "pony_express")
    public WebElement PonyExpressPhoto;

    @FindBy (id = "checkout")
    public WebElement Checkout;

    @FindBy (css = ".error-message-container.error")
    public WebElement ErrorFirstName;

    @FindBy (className = "summary_total_label")
    public WebElement  TotalSum;
    //------------------------------------------------------

    public void clickOnCheckoutButton () {
        CheckoutButton.click();
    }

    public String getTitle () {
        return Title.getText();
    }

    public void clickOnCancelButton () {
        CancelButton.click();
    }

    public void enterFirstName (String firstName) {
        FirstNameField.clear();
        FirstNameField.sendKeys(firstName);
    }

    public void enterLastName (String lastName) {
        LastNameField.clear();
        LastNameField.sendKeys(lastName);
    }

    public void enterPostalCode (String postalCode) {
        PostalCode.clear();
        PostalCode.sendKeys(postalCode);
    }

    public void clickContinue () {
        ContinueButton.click();
    }

    public void clickOnFinishButton () {
        FinishButton.click();
    }

    public void leaveFirstNameBlank () {
        FirstNameField.clear();
    }

    public void leaveLastNameBlank () {
        LastNameField.clear();
    }

    public void leavePostalCodeBlank() {
        PostalCode.clear();
    }

    public void clickCheckout () {
        Checkout.click();
    }

    public String getFirstNameError () {
       return ErrorFirstName.getText();
    }

    public String getTotalSum () {
        return TotalSum.getText();
    }
}
