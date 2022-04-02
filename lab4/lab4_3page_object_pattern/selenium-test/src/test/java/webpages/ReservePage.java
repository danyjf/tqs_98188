package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReservePage {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[3]/td[1]/input")
    private WebElement flightButton;

    public ReservePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickFlightButton() {
        flightButton.click();
    }
}
