package tests;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import webpages.ConfirmationPage;
import webpages.HomePage;
import webpages.PurchasePage;
import webpages.ReservePage;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class BlazeDemoTest {
    @Test
    public void blazeDemo(FirefoxDriver driver) {
        HomePage home = new HomePage(driver);
        home.selectFromPortOption("Portland");
        home.selectToPortOption("Dublin");
        home.clickFindFlightsButton();

        ReservePage reservePage = new ReservePage(driver);
        reservePage.clickFlightButton();

        PurchasePage purchasePage = new PurchasePage(driver);
        purchasePage.setName("Selenium");
        purchasePage.setAddress("Test Address");
        purchasePage.setCity("Test City");
        purchasePage.setState("Test State");
        purchasePage.setZipCode("00000");
        purchasePage.selectCardType("American Express");
        purchasePage.setCreditCardNumber("012301230123");
        purchasePage.setCreditCardMonth("10");
        purchasePage.setCreditCardYear("2023");
        purchasePage.setNameOnCard("Selenium Test");
        purchasePage.checkRememberMeCheckbox();

        assertThat(purchasePage.isNameCorrect("Selenium"), is(true));
        assertThat(purchasePage.isAddressCorrect("Test Address"), is(true));
        assertThat(purchasePage.isCityCorrect("Test City"), is(true));
        assertThat(purchasePage.isStateCorrect("Test State"), is(true));
        assertThat(purchasePage.isZipCodeCorrect("00000"), is(true));
        assertThat(purchasePage.isCardTypeCorrect("American Express"), is(true));
        assertThat(purchasePage.isCreditCardNumberCorrect("012301230123"), is(true));
        assertThat(purchasePage.isCreditCardMonthCorrect("10"), is(true));
        assertThat(purchasePage.isCreditCardYearCorrect("2023"), is(true));
        assertThat(purchasePage.isNameOnCardCorrect("Selenium Test"), is(true));
        assertThat(purchasePage.isRememberMeSelected(), is(true));

        purchasePage.clickPurchaseButton();

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        assertThat(confirmationPage.isPageTitle("BlazeDemo Confirmation"), is(true));
    }
}
