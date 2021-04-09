package co.com.swaglabs.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import co.com.swaglabs.log.Log4j;

public class Hooks {

    public static WebDriver driver;
    public static Log4j log4j;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        try {
            log4j = new Log4j(Hooks.class.getName());
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            log4j.getLogger().info("Opening website https://www.saucedemo.com/");
        } catch (WebDriverException webEx) {
            log4j.getLogger().info("WebDriver Failed: " + webEx.getMessage());
        } catch (Exception ex) {
            log4j.getLogger().fatal(ex.getMessage());
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
