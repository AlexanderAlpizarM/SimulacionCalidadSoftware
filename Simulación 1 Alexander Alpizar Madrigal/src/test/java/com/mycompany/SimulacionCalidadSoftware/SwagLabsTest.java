package com.mycompany.SimulacionCalidadSoftware;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class SwagLabsTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage overviewPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        overviewPage = new CheckoutOverviewPage(driver);

        loginPage.loginUser("standard_user", "secret_sauce");
        assertTrue(inventoryPage.isLoaded(), "El inicio de sesión no llevó al inventario.");
    }

    // CP01: Ordenamiento de productos por precio
    @Test
    public void cp01_OrdenamientoPorPrecio() {
        inventoryPage.sortBy("lohi");

        List<Double> obtenidos = inventoryPage.getPrices();
        List<Double> esperados = new ArrayList<>(obtenidos);
        Collections.sort(esperados);

        assertEquals(6, obtenidos.size(), "Se esperaban 6 productos en el inventario.");
        assertEquals(esperados, obtenidos, "Los precios no están ordenados de menor a mayor.");
    }

    // CP02: Visualización de los detalles de un producto
    @Test
    public void cp02_DetallesDeProducto() {
        inventoryPage.openProduct("Sauce Labs Backpack");

        assertEquals("Sauce Labs Backpack", productPage.getName());
        assertEquals("$29.99", productPage.getPrice());
        assertFalse(productPage.getDescription().isEmpty(), "La descripción está vacía.");
        assertTrue(productPage.isImageDisplayed(), "La imagen del producto no se muestra.");
    }

    // CP03: Agregar varios productos al carrito
    @Test
    public void cp03_AgregarVariosProductos() {
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();

        assertEquals("2", inventoryPage.getCartBadgeText());
    }

    // CP04: Verificación del contenido del carrito
    @Test
    public void cp04_ContenidoDelCarrito() {
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        inventoryPage.goToCart();

        assertEquals(List.of("Sauce Labs Backpack", "Sauce Labs Bike Light"), cartPage.getItemNames());
        assertEquals(List.of("1", "1"), cartPage.getItemQuantities());
        assertEquals(List.of("$29.99", "$9.99"), cartPage.getItemPrices());
    }

    // CP05: Regresar al inventario desde el carrito
    @Test
    public void cp05_RegresarAlInventario() {
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();
        cartPage.clickContinueShopping();

        assertTrue(inventoryPage.isLoaded(), "No se regresó a la página del inventario.");
    }

    // CP06: Validación del resumen de compra
    @Test
    public void cp06_ResumenDeCompra() {
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        inventoryPage.goToCart();
        cartPage.clickCheckout();
        checkoutPage.fillInformation("Alexander", "Alpizar", "10101");

        assertEquals(List.of("Sauce Labs Backpack", "Sauce Labs Bike Light"), overviewPage.getItemNames());
        assertEquals("Item total: $39.98", overviewPage.getSubtotal());
        assertEquals("Tax: $3.20", overviewPage.getTax());
        assertEquals("Total: $43.18", overviewPage.getTotal());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
