import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class Lab42Test {
  private WebDriver driver;

  @BeforeAll
  static void setupClass() {
    WebDriverManager.firefoxdriver().setup();
  }

  @BeforeEach
  public void setUp() {
    driver = new FirefoxDriver();
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void lab42() {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(550, 691));
    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Portland']")).click();
    }
    {
      String value = driver.findElement(By.name("fromPort")).getAttribute("value");
      assertThat(value, is("Portland"));
    }
    driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(4)")).click();
    driver.findElement(By.name("toPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'Dublin']")).click();
    }
    {
      String value = driver.findElement(By.name("toPort")).getAttribute("value");
      assertThat(value, is("Dublin"));
    }
    driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(6)")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Selenium");
    driver.findElement(By.cssSelector("p:nth-child(8)")).click();
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("Test Address");
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("Test City");
    driver.findElement(By.id("state")).click();
    driver.findElement(By.id("state")).sendKeys("Test State");
    driver.findElement(By.cssSelector(".control-group:nth-child(5) > .control-label")).click();
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("00000");
    driver.findElement(By.id("cardType")).click();
    {
      WebElement dropdown = driver.findElement(By.id("cardType"));
      dropdown.findElement(By.xpath("//option[. = 'American Express']")).click();
    }
    driver.findElement(By.cssSelector("option:nth-child(2)")).click();
    driver.findElement(By.id("creditCardNumber")).click();
    driver.findElement(By.id("creditCardNumber")).sendKeys("012301230123");
    driver.findElement(By.id("creditCardMonth")).click();
    driver.findElement(By.id("creditCardMonth")).clear();
    driver.findElement(By.id("creditCardMonth")).sendKeys("10");
    driver.findElement(By.id("creditCardYear")).click();
    driver.findElement(By.id("creditCardYear")).clear();
    driver.findElement(By.id("creditCardYear")).sendKeys("2023");
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.id("nameOnCard")).sendKeys("Selenium Test");
    driver.findElement(By.cssSelector(".control-group:nth-child(11) > .control-label")).click();
    driver.findElement(By.id("rememberMe")).click();
    {
      String value = driver.findElement(By.id("inputName")).getAttribute("value");
      assertThat(value, is("Selenium"));
    }
    {
      String value = driver.findElement(By.id("address")).getAttribute("value");
      assertThat(value, is("Test Address"));
    }
    {
      String value = driver.findElement(By.id("city")).getAttribute("value");
      assertThat(value, is("Test City"));
    }
    {
      String value = driver.findElement(By.id("state")).getAttribute("value");
      assertThat(value, is("Test State"));
    }
    {
      String value = driver.findElement(By.id("zipCode")).getAttribute("value");
      assertThat(value, is("00000"));
    }
    {
      WebElement element = driver.findElement(By.id("cardType"));
      String value = element.getAttribute("value");
      String locator = String.format("option[@value='%s']", value);
      String selectedText = element.findElement(By.xpath(locator)).getText();
      assertThat(selectedText, is("American Express"));
    }
    {
      String value = driver.findElement(By.id("creditCardNumber")).getAttribute("value");
      assertThat(value, is("012301230123"));
    }
    {
      String value = driver.findElement(By.id("creditCardMonth")).getAttribute("value");
      assertThat(value, is("10"));
    }
    {
      String value = driver.findElement(By.id("creditCardYear")).getAttribute("value");
      assertThat(value, is("2023"));
    }
    {
      String value = driver.findElement(By.id("nameOnCard")).getAttribute("value");
      assertThat(value, is("Selenium Test"));
    }
    assertTrue(driver.findElement(By.id("rememberMe")).isSelected());
    driver.findElement(By.cssSelector(".btn-primary")).click();
    assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
  }
}
