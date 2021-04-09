package co.com.swaglabs.stepdefinitions;

import co.com.swaglabs.pages.LoginPage;
import co.com.swaglabs.utils.Constants;
import co.com.swaglabs.utils.DataGenerator;
import co.com.swaglabs.utils.ScreenshotsHandler;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.PageFactory;
import static co.com.swaglabs.stepdefinitions.Hooks.driver;
import static co.com.swaglabs.stepdefinitions.Hooks.log4j;

import java.util.Locale;

public class StepUserInvalid {

    private String suiteEvidenceRoute;
    @Before
    public void setUp() {
        suiteEvidenceRoute = Constants.evidenceRoute + "Login\\LoginInvalid\\" + DataGenerator.getCurrentDate();
    }

    @When("user aggregate invalid credentials")
    public void userAggregateInvalidCredentials() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginFaker();
        log4j.getLogger().info("Aggregate credentials user invalid");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "ClientAutenticateLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Then("you are answered with a invalid user message")
    public void youAreAnsweredWithAInvalidUserMessage() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        final String expected = "epic sadface: username and password do not match any user in this service";
        String actual = loginPage.validateMessageUserAutentication();
        System.out.println("Mensaje actual: "+ actual);
        Assertions.assertEquals(expected, actual.toLowerCase(Locale.ROOT));
        log4j.getLogger().info("Validate message user invalid");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "MessageUserInvalidLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
