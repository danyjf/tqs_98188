package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PurchasePage {
    private WebDriver driver;

    @FindBy(id = "inputName")
    private WebElement name;

    @FindBy(id = "address")
    private WebElement address;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "zipCode")
    private WebElement zipCode;

    @FindBy(id = "cardType")
    private WebElement cardTypeDropdown;

    @FindBy(id = "creditCardNumber")
    private WebElement creditCardNumber;

    @FindBy(id = "creditCardMonth")
    private WebElement creditCardMonth;

    @FindBy(id = "creditCardYear")
    private WebElement creditCardYear;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCard;

    @FindBy(id = "rememberMe")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "/html/body/div[2]/form/div[11]/div/input")
    private WebElement purchaseButton;

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setName(String input) {
        name.click();
        name.sendKeys(input);
    }

    public void setAddress(String input) {
        address.click();
        address.sendKeys(input);
    }

    public void setCity(String input) {
        city.click();
        city.sendKeys(input);
    }

    public void setState(String input) {
        state.click();
        state.sendKeys(input);
    }

    public void setZipCode(String input) {
        zipCode.click();
        zipCode.sendKeys(input);
    }

    public void selectCardType(String option) {
        Select drop = new Select(cardTypeDropdown);
        drop.selectByVisibleText(option);
    }

    public void setCreditCardNumber(String input) {
        creditCardNumber.click();
        creditCardNumber.sendKeys(input);
    }

    public void setCreditCardMonth(String input) {
        creditCardMonth.click();
        creditCardMonth.clear();
        creditCardMonth.sendKeys(input);
    }

    public void setCreditCardYear(String input) {
        creditCardYear.click();
        creditCardYear.clear();
        creditCardYear.sendKeys(input);
    }

    public void setNameOnCard(String input) {
        nameOnCard.click();
        nameOnCard.sendKeys(input);
    }

    public void checkRememberMeCheckbox() {
        rememberMeCheckbox.click();
    }

    public void clickPurchaseButton() {
        purchaseButton.click();
    }

    public boolean isNameCorrect(String input) {
        return name.getAttribute("value").equals(input);
    }

    public boolean isAddressCorrect(String input) {
        return address.getAttribute("value").equals(input);
    }

    public boolean isCityCorrect(String input) {
        return city.getAttribute("value").equals(input);
    }

    public boolean isStateCorrect(String input) {
        return state.getAttribute("value").equals(input);
    }

    public boolean isZipCodeCorrect(String input) {
        return zipCode.getAttribute("value").equals(input);
    }

    public boolean isCardTypeCorrect(String input) {
        String value = cardTypeDropdown.getAttribute("value");
        String locator = String.format("option[@value='%s']", value);
        String selectedText = cardTypeDropdown.findElement(By.xpath(locator)).getText();
        return selectedText.equals(input);
    }

    public boolean isCreditCardNumberCorrect(String input) {
        return creditCardNumber.getAttribute("value").equals(input);
    }

    public boolean isCreditCardMonthCorrect(String input) {
        return creditCardMonth.getAttribute("value").equals(input);
    }

    public boolean isCreditCardYearCorrect(String input) {
        return creditCardYear.getAttribute("value").equals(input);
    }

    public boolean isNameOnCardCorrect(String input) {
        return nameOnCard.getAttribute("value").equals(input);
    }

    public boolean isRememberMeSelected() {
        return rememberMeCheckbox.isSelected();
    }
}
