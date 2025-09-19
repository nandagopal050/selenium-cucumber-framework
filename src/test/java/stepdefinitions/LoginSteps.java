package stepdefinitions;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginSteps {

    private WebDriver driver = null;
    private static final By userId=By.id("user-name");
    private static final By password=By.id("password");
    private static final By login=By.id("login-button");
    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

    }

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        driver.findElement(userId).sendKeys("standard_user");
        driver.findElement(password).sendKeys("secret_sauce");
    }

    @When("clicks on the login button")
    public void clicks_on_the_login_button() {
        driver.findElement(login).click();
    }

    @Then("user is navigated to the home page")
    public void user_is_navigated_to_the_home_page() {
        Assert.assertEquals("Swag Labs", driver.getTitle());
    }
}
