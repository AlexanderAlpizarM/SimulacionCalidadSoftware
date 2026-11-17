package com.mycompany.SimulacionCalidadSoftware;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    private By name = By.cssSelector("[data-test='inventory-item-name']");
    private By description = By.cssSelector("[data-test='inventory-item-desc']");
    private By price = By.cssSelector("[data-test='inventory-item-price']");
    private By image = By.className("inventory_details_img");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getName() { return getText(name); }
    public String getDescription() { return getText(description); }
    public String getPrice() { return getText(price); }

    public boolean isImageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(image)).isDisplayed();
    }
}
