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

public class StepEmptyFields {

    private String suiteEvidenceRoute;
    @Before
    public void setUp() {
        suiteEvidenceRoute = Constants.evidenceRoute + "Login\\LoginEmptyFileds\\" + DataGenerator.getCurrentDate();
    }

    @When("does not add username and password")
    public void doesNotAddUsernameAndPassword() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginSuccess("","");
        log4j.getLogger().info("Aggregate credentials Empty");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "AutenticateEmptyLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        loginPage.clickBtnLogin();
    }

    @Then("you are answered with a message of empty fields")
    public void youAreAnsweredWithAMessageOfEmptyFields() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        final String expected = "epic sadface: username is required";
        String actual = loginPage.validateMessageUserAutentication();
        System.out.println("Message actual: "+ actual);
        Assertions.assertEquals(expected, actual.toLowerCase(Locale.ROOT));
        log4j.getLogger().info("Validate message empty fields");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "MessageEmptityFieldsLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
