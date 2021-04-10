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

import java.util.Locale;

import static co.com.swaglabs.stepdefinitions.Hooks.driver;
import static co.com.swaglabs.stepdefinitions.Hooks.log4j;

public class StepEmptyPasswordField {

    private String suiteEvidenceRoute;
    @Before
    public void setUp() {
        suiteEvidenceRoute = Constants.evidenceRoute + "Login\\LoginEmptyFileds\\" + DataGenerator.getCurrentDate();
    }

    @When("does not add password")
    public void doesNotAddPassword() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginSuccess("standard_user","");
        log4j.getLogger().info("Aggregate empty password field");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "AutenticateEmptyPasswordFieldLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        loginPage.clickBtnLogin();
    }

    @Then("you are answered with an empty password field message")
    public void youAreAnsweredWithAnEmptyPasswordFieldMessage() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        final String expected = "epic sadface: p    assword is required";
        String actual = loginPage.validateMessageUserAutentication();
        Assertions.assertEquals(expected, actual.toLowerCase(Locale.ROOT));
        log4j.getLogger().info("Validate message empty password field");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "MessageEmptyPasswordFieldLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
