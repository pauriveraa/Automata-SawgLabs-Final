package co.com.swaglabs.stepdefinitions;

import co.com.swaglabs.data.basededatos.DataUser;
import co.com.swaglabs.data.basededatos.DataUserDTO;
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
import java.util.Map;
import static co.com.swaglabs.stepdefinitions.Hooks.driver;
import static co.com.swaglabs.stepdefinitions.Hooks.log4j;

public class StepUserLocked {

    private String suiteEvidenceRoute;
    @Before
    public void setUp() {
        suiteEvidenceRoute = Constants.evidenceRoute + "Login\\LoginLocked\\" + DataGenerator.getCurrentDate();
    }

    Map<Integer, DataUser> userMap = new DataUserDTO().getUsers();

    @When("user aggregate credentials")
    public void userAggregateCredentials() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.sendInfoUserName(userMap.get(3).getUsername());
        loginPage.sendIfoPassword(userMap.get(3).getPassword());
        log4j.getLogger().info("Aggregate credentials user locked");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "ClientAutenticateLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        loginPage.clickBtnLogin();
    }

    @Then("you are answered with a blocked user message")
    public void youAreAnsweredWithABlockedUserMessage() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        final String expected = "epic sadface: sorry, this user has been locked out.";
        String actual = loginPage.validateMessageUserAutentication();
        System.out.println("Message actual: "+ actual);
        Assertions.assertEquals(expected, actual.toLowerCase(Locale.ROOT));
        log4j.getLogger().info("Validate message user locked");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "MessageUserLockedLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
