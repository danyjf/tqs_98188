package pt.ua.tqs.tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SearchFlightsSteps {
    private WebDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver = WebDriverManager.firefoxdriver().create();
        driver.get(url);
    }

    @And("I select flights from {string} to {string}")
    public void selectFlights(String from, String to) {
        WebElement fromPort = driver.findElement(By.name("fromPort"));
        Select dropFrom = new Select(fromPort);
        dropFrom.selectByVisibleText(from);

        WebElement toPort = driver.findElement(By.name("toPort"));
        Select dropTo = new Select(toPort);
        dropTo.selectByVisibleText(to);
    }

    @And("I click Find Flights")
    public void findFlights() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("page title should be {string}")
    public void titleShouldBe(String title) {
        assertThat(driver.getTitle(), is(title));
    }
}
