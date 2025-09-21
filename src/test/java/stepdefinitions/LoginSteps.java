package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; // <-- NEW IMPORT
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.util.HashMap; // <-- NEW IMPORT
import java.util.Map;   // <-- NEW IMPORT

public class LoginSteps {

    private WebDriver driver = null;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CheckoutPage checkoutPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        // --- THIS IS THE FIX ---
        // Create a map to store preferences
        Map<String, Object> prefs = new HashMap<String, Object>();
        // Add key and value to map to disable password manager pop-up
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        // Create an instance of ChromeOptions
        ChromeOptions options = new ChromeOptions();
        // Add the preferences to the options
        options.setExperimentalOption("prefs", prefs);

        // Pass the options object to the ChromeDriver constructor
        driver = new ChromeDriver(options);
        // --- END OF FIX ---

        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
    }

    @And("clicks on the login button")
    public void clicks_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("user is navigated to the home page")
    public void user_is_navigated_to_the_home_page() {
        Assert.assertTrue("User was not navigated to the home page.", productsPage.isProductsTitleVisible());
    }

    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("wrong_password");
    }

    @Then("user should see an error message indicating invalid credentials")
    public void user_should_see_an_error_message_indicating_invalid_credentials() {
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals("The error message was not correct.", expectedErrorMessage, loginPage.getErrorMessage());
    }

    @When("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
    }

    @And("user adds a backpack to the cart")
    public void user_adds_a_backpack_to_the_cart() {
        productsPage.addBackpackToCart();
        productsPage.goToShoppingCart();
    }

    @And("user completes the checkout process")
    public void user_completes_the_checkout_process() {
        checkoutPage.proceedToCheckout();
        checkoutPage.enterUserDetails("Gopal", "J", "560001");
        checkoutPage.continueCheckout();
        checkoutPage.finishCheckout();
    }

    @Then("user should see the order confirmation message")
    public void user_should_see_the_order_confirmation_message() {
        String expectedMessage = "Thank you for your order!";
        Assert.assertEquals("Order confirmation message was not correct.", expectedMessage, checkoutPage.getConfirmationMessage());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}