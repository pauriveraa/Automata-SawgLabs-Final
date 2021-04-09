package co.com.swaglabs.stepdefinitions;

import co.com.swaglabs.data.basededatos.DataUser;
import co.com.swaglabs.data.basededatos.DataUserDTO;
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
import java.util.Locale;
import java.util.Map;
import static co.com.swaglabs.stepdefinitions.Hooks.driver;

public class StepUserLocked {

    //LoginPage loginPage;

    private String suiteEvidenceRoute;
    @Before
    public void setUp() {
        suiteEvidenceRoute = Constants.evidenceRoute + "Login\\LoginLocked\\" + DataGenerator.getCurrentDate();
    }

    Map<Integer, DataUser> userMap = new DataUserDTO().getUsers();

    @Given("que un cliente potencial conoce la ruta para ingresar a la aplicacion")
    public void queUnClientePotencialConoceLaRutaParaIngresarALaAplicacion() {
        driver.get("https://www.saucedemo.com/");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "ClientInHomeLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @When("ingresa sus credenciales")
    public void ingresaSusCredenciales() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.sendInfoUserName(userMap.get(3).getUsername());
        loginPage.sendIfoPassword(userMap.get(3).getPassword());
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "ClientAutenticateLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        loginPage.clickBtnLogin();
    }

    @Then("se le responde con un mensaje de usuario bloqueado")
    public void seLeRespondeConUnMensajeDeUsuarioBloqueado() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        final String expected = "epic sadface: sorry, this user has been locked out.";
        String actual = loginPage.validateMessageUserAutentication();
        System.out.println("Mensaje actual: "+ actual);
        Assertions.assertEquals(expected, actual.toLowerCase(Locale.ROOT));
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "MessageUserLockedLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
