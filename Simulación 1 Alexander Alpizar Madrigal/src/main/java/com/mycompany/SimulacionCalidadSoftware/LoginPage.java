package com.mycompany.SimulacionCalidadSoftware;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Herencia de BasePage
public class LoginPage extends BasePage {

    // Identificadores de los elementos de la página
    private By userLocator = By.id("user-name");
    private By passLocator = By.id("password");
    private By loginBtnLocator = By.id("login-button");
    private By errorLocator = By.cssSelector("[data-test='error']");

    // Constructor que invoca al constructor de la clase padre
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Método para ejecutar la acción de login
    public void loginUser(String user, String pass) {
        type(user, userLocator);
        type(pass, passLocator);
        click(loginBtnLocator);
    }
    
    // Método para capturar mensajes de error
    public String getErrorMessage() {
        return getText(errorLocator);
    }
}