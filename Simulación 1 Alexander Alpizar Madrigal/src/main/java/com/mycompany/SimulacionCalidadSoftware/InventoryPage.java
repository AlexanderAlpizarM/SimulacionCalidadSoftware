package com.mycompany.SimulacionCalidadSoftware;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends BasePage {

    private By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
    private By itemPrices = By.className("inventory_item_price");
    private By addBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private By addBikeLightBtn = By.id("add-to-cart-sauce-labs-bike-light");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartLink = By.className("shopping_cart_link");
    private By inventoryList = By.className("inventory_list");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryList));
        return driver.getCurrentUrl().contains("inventory.html");
    }

    // value: "lohi" (precio menor a mayor), "hilo", "az", "za"
    public void sortBy(String value) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        new Select(dropdown).selectByValue(value);
    }

    public List<Double> getPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement e : findAll(itemPrices)) {
            prices.add(Double.parseDouble(e.getText().replace("$", "")));
        }
        return prices;
    }

    public void openProduct(String productName) {
        click(By.linkText(productName));
    }

    public void addBackpackToCart() {
        click(addBackpackBtn);
    }

    public void addBikeLightToCart() {
        click(addBikeLightBtn);
    }

    // Si el carrito está vacío el badge no existe en el HTML, por eso se devuelve "".
    public String getCartBadgeText() {
        List<WebElement> badge = findAll(cartBadge);
        return badge.isEmpty() ? "" : badge.get(0).getText();
    }

    public void goToCart() {
        click(cartLink);
    }
}
