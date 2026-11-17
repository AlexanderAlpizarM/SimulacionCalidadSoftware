package com.mycompany.SimulacionCalidadSoftware;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    private By checkoutBtn = By.id("checkout");
    private By continueShoppingBtn = By.id("continue-shopping");
    private By itemNames = By.className("inventory_item_name");
    private By itemPrices = By.className("inventory_item_price");
    private By itemQuantities = By.className("cart_quantity");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckout() { click(checkoutBtn); }

    public void clickContinueShopping() { click(continueShoppingBtn); }

    public List<String> getItemNames() { return texts(itemNames); }
    public List<String> getItemPrices() { return texts(itemPrices); }
    public List<String> getItemQuantities() { return texts(itemQuantities); }

    private List<String> texts(By locator) {
        List<String> result = new ArrayList<>();
        for (WebElement e : findAll(locator)) {
            result.add(e.getText());
        }
        return result;
    }
}
