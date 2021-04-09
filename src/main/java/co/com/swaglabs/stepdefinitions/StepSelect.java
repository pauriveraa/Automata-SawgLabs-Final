package co.com.swaglabs.stepdefinitions;

import co.com.swaglabs.pages.SelectPage;
import co.com.swaglabs.utils.Constants;
import co.com.swaglabs.utils.DataGenerator;
import co.com.swaglabs.utils.ScreenshotsHandler;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.PageFactory;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import static co.com.swaglabs.stepdefinitions.Hooks.driver;
import static co.com.swaglabs.stepdefinitions.Hooks.log4j;

public class StepSelect {

    private String suiteEvidenceRoute;
    @Before
    public void setUp() {
        suiteEvidenceRoute = Constants.evidenceRoute + "Select\\" + DataGenerator.getCurrentDate();
    }


    @When("choose the products you want to buy")
    public void chooseTheProductsYouWantToBuy() {
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
            log4j.getLogger().info("Aggregate products in the cart");
        }
    }

    @Then("will then be displayed in the shopping cart")
    public void willThenBeDisplayedInTheShoppingCart() {
        SelectPage selectpage = PageFactory.initElements(driver, SelectPage.class);
        selectpage.clicBtnCart();
        log4j.getLogger().info("Click in the button cart");
        try {
            ScreenshotsHandler.takeSnapShot(driver, suiteEvidenceRoute, "ClickButtonCartSelectPage.png");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        final String expected = "your cart";
        String actual = selectpage.validateMessageProducts();
        Assertions.assertEquals(expected, actual.toLowerCase(Locale.ROOT));
        log4j.getLogger().info("Validate message page products");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
