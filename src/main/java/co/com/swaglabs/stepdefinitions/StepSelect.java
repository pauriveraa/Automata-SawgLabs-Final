package co.com.swaglabs.stepdefinitions;

import co.com.swaglabs.pages.LoginPage;
import co.com.swaglabs.pages.SelectPage;
import co.com.swaglabs.utils.Constants;
import co.com.swaglabs.utils.DataGenerator;
import co.com.swaglabs.utils.ScreenshotsHandler;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static co.com.swaglabs.stepdefinitions.Hooks.driver;

public class StepSelect {

    private String suiteEvidenceRoute;
    @Before
    public void setUp() {
        suiteEvidenceRoute = Constants.evidenceRoute + "Select\\" + DataGenerator.getCurrentDate();
    }

    @Given("que un cliente potencial conoce la ruta de autenticacion de la pagina")
    public void queUnClientePotencialConoceLaRutaDeAutenticacionDeLaPagina() {
        driver.get("https://www.saucedemo.com/");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "ClientInHomeSelectPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @When("el usuario ingresa credenciales validas para comprar")
    public void elUsuarioIngresaCredencialesValidasParaComprar() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginSuccess("standard_user","secret_sauce");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "AggregateCredentialsSelectPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @When("escoge los productos que desea comprar")
    public void escogeLosProductosQueDeseaComprar() {
        SelectPage selectpage = PageFactory.initElements(driver, SelectPage.class);
        int tamano = selectpage.getProducts().size();
        int cantidadProductos = (int)Math.floor(Math.random()*tamano+1);

        Set<Integer> alreadyUsedNumbers = new HashSet<>();
        for(int i=1; i<(cantidadProductos+1);i++){
            int aleatorio = (int)(Math.random()*tamano);
            System.out.println("Posicion: "+aleatorio);
            if (!alreadyUsedNumbers.contains(aleatorio)){
                alreadyUsedNumbers.add(aleatorio);
                selectpage.getProducts().get(aleatorio).click();
                try {
                    ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "SelectProductsSelectPage.png");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @Then("se visualizaran en el carrito de compras")
    public void seVisualizaranEnElCarritoDeCompras() {
        SelectPage selectpage = PageFactory.initElements(driver, SelectPage.class);
        selectpage.clicBtnCart();
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "ClickButtonCartSelectPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        final String expected = "your cart";
        String actual = selectpage.validateMessageProducts();
        Assertions.assertEquals(expected, actual.toLowerCase(Locale.ROOT));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
