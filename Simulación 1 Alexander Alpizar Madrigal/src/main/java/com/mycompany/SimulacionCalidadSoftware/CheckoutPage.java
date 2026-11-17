package com.mycompany.SimulacionCalidadSoftware;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By zipInput = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By errorMsg = By.cssSelector("[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillInformation(String fName, String lName, String zip) {
        type(fName, firstNameInput);
        type(lName, lastNameInput);
        type(zip, zipInput);
        click(continueBtn);
    }

    public String getErrorMessage() {
        return getText(errorMsg);
    }
}