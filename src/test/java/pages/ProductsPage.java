package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    private WebDriver driver;

    // Locators
    private By productsTitle = By.className("title");
    private By addToCartBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private By shoppingCartLink = By.className("shopping_cart_link");

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public boolean isProductsTitleVisible() {
        return driver.findElement(productsTitle).isDisplayed();
    }

    public void addBackpackToCart() {
        driver.findElement(addToCartBackpackButton).click();
    }

    public void goToShoppingCart() {
        driver.findElement(shoppingCartLink).click();
    }
}