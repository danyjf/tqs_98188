package pt.ua.tqs.covidincidence.functional;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

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

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("totalCases")).getText().length() != 0;
            }
        });
    }

    @Then("total number of cases should be {string}")
    public void numberOfCasesShouldBe(String expectedTotalCases) {
        assertThat(driver.findElement(By.id("totalCases")).getText()).isEqualTo(expectedTotalCases);
    }

    @And("total number of deaths should be {string}")
    public void numberOfDeathsShouldBe(String expectedTotalDeaths) {
        assertThat(driver.findElement(By.id("totalDeaths")).getText()).isEqualTo(expectedTotalDeaths);
    }

    @And("total number of tests should be {string}")
    public void numberOfTestsShouldBe(String expectedTotalTests) {
        assertThat(driver.findElement(By.id("totalTests")).getText()).isEqualTo(expectedTotalTests);
    }
}
