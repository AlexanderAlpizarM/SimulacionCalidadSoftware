package com.mycompany.SimulacionCalidadSoftware;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage extends BasePage {

    private By finishBtn = By.id("finish");
    private By completeHeader = By.className("complete-header");
    private By itemNames = By.className("inventory_item_name");
    private By subtotal = By.className("summary_subtotal_label");
    private By tax = By.className("summary_tax_label");
    private By total = By.className("summary_total_label");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void clickFinish() { click(finishBtn); }

    public String getCompleteMessage() { return getText(completeHeader); }

    public List<String> getItemNames() {
        getText(subtotal); // espera a que la pantalla de resumen cargue
        List<String> names = new ArrayList<>();
        for (WebElement e : findAll(itemNames)) {
            names.add(e.getText());
        }
        return names;
    }

    public String getSubtotal() { return getText(subtotal); }
    public String getTax() { return getText(tax); }
    public String getTotal() { return getText(total); }
}
