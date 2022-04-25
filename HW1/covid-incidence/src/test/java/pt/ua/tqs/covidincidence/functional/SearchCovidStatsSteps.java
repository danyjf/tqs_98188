package pt.ua.tqs.covidincidence.functional;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SearchCovidStatsSteps {
    private WebDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(url);
    }

    @And("I select the country {string} and the date {string}")
    public void iSelectCountryAndDate(String country, String date) {
        WebElement selectCountry = driver.findElement(By.id("country"));
        Select dropCountry = new Select(selectCountry);
        dropCountry.selectByVisibleText(country);

        driver.findElement(By.id("date")).sendKeys(date);
    }

    @And("I click the Search button")
    public void iClickTheSearchButton() {
        driver.findElement(By.id("search")).click();
    }

    @Then("total number of cases should be {int}")
    public void numberOfCasesShouldBe(int expectedTotalCases) {

    }

    @And("total number of deaths should be {int}")
    public void numberOfDeathsShouldBe(int expectedTotalDeaths) {

    }

    @And("total number of tests should be {int}")
    public void numberOfTestsShouldBe(int expectedTotalTests) {

    }
}
