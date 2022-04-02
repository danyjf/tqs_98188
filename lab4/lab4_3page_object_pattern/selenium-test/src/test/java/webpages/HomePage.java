package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    private WebDriver driver;

    private static String PAGE_URL = "https://blazedemo.com/";

    @FindBy(name = "fromPort")
    private WebElement fromPortDropdown;

    @FindBy(name = "toPort")
    private WebElement toPortDropdown;

    @FindBy(xpath = "/html/body/div[3]/form/div/input")
    private WebElement findFlightsButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void selectFromPortOption(String option) {
        Select drop = new Select(fromPortDropdown);
        drop.selectByVisibleText(option);
    }

    public void selectToPortOption(String option) {
        Select drop = new Select(toPortDropdown);
        drop.selectByVisibleText(option);
    }

    public void clickFindFlightsButton() {
        findFlightsButton.click();
    }
}
