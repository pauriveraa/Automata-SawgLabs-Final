package co.com.swaglabs.stepdefinitions;

import co.com.swaglabs.pages.LoginPage;
import co.com.swaglabs.utils.Constants;
import co.com.swaglabs.utils.DataGenerator;
import co.com.swaglabs.utils.ScreenshotsHandler;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.PageFactory;
import static co.com.swaglabs.stepdefinitions.Hooks.driver;

import java.util.Locale;

public class StepUserInvalid {

    private String suiteEvidenceRoute;
    @Before
    public void setUp() {
        suiteEvidenceRoute = Constants.evidenceRoute + "Login\\LoginInvalid\\" + DataGenerator.getCurrentDate();
    }

    @Given("que un cliente conoce la ruta para ingresar a la aplicacion")
    public void queUnClienteConoceLaRutaParaIngresarALaAplicacion() {
        driver.get("https://www.saucedemo.com/");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "ClientInHomeLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @When("ingresa credenciales incorrectas")
    public void ingresaCredencialesIncorrectas() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginFaker();
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "ClientAutenticateLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Then("se le responde con un mensaje de usuario o contrasena invalida")
    public void seLeRespondeConUnMensajeDeUsuarioOContrasenaInvalida() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        final String expected = "epic sadface: username and password do not match any user in this service";
        String actual = loginPage.validateMessageUserAutentication();
        System.out.println("Mensaje actual: "+ actual);
        Assertions.assertEquals(expected, actual.toLowerCase(Locale.ROOT));
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "MessageUserInvalidLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
