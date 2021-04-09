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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import static co.com.swaglabs.stepdefinitions.Hooks.driver;
import static co.com.swaglabs.stepdefinitions.Hooks.log4j;

import java.util.Locale;
import java.util.Map;

public class StepLogin {

    private String suiteEvidenceRoute;
    @Before
    public void setUp() {
        suiteEvidenceRoute = Constants.evidenceRoute + "Login\\LoginSucces\\" + DataGenerator.getCurrentDate();
    }

    Map<Integer, DataUser> userMap = new DataUserDTO().getUsers();
    @Given("a potential customer knows the authentication path")
    public void aPotentialCustomerKnowsTheAuthenticationPath() {
        driver.get("https://www.saucedemo.com/");
        log4j.getLogger().info("Abrir el enlace https://www.saucedemo.com/");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "ClientInHomeLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @When("user enters valid credentials")
    public void userEntersValidCredentials()  {
        try{
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            loginPage.sendInfoUserName(userMap.get(1).getUsername());
            log4j.getLogger().info("Aggregate user");

            loginPage.sendIfoPassword(userMap.get(1).getPassword());
            log4j.getLogger().info("Aggregate password");

            try {
                ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "ClientAggregateCredentialsLoginPage.png");
            } catch (Exception exception) {
                exception.printStackTrace();

            }
            loginPage.clickBtnLogin();
            try {
                ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "ClientClickButtonLoginPage.png");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }catch (NoSuchElementException ne){
            log4j.getLogger().error("WebElement not found");
        }catch(WebDriverException we){
            log4j.getLogger().error("WebDriver failed: " + we.getAdditionalInformation());
        }catch (Exception ex){
            log4j.getLogger().fatal(ex.getMessage());
        }
    }

    @Then("i would have a correct authentication")
    public void iWouldHaveACorrectAuthentication() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        final String expected = "products";
        String actual = loginPage.validateMessageProducts();
        Assertions.assertEquals(expected, actual.toLowerCase(Locale.ROOT));
        log4j.getLogger().info("Validate page products");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "ClientAutenticateSuccessLoginPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
