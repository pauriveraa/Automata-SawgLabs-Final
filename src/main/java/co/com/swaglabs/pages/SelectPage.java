package co.com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class SelectPage {

    WebDriver driver;
    FluentWait<WebDriver> wait;

    @FindBys(@FindBy(xpath = "//div[@class='pricebar']/button"))
    List<WebElement> buttonAddToCart;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement btnCart;

    @FindBy(xpath = "//span[@class='title']")
    WebElement messageYourCarText;

    public SelectPage(WebDriver driver){
        this.driver = driver;
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
    }

    public List<WebElement> getProducts(){
        System.out.println(buttonAddToCart);
        return buttonAddToCart;
    }

    public void clickBtnAddToCart(int aleatorio){
        buttonAddToCart.get(aleatorio).click();
    }

    public int getAddToCartoNumber(){
        return buttonAddToCart.size();
    }

    public void clicBtnCart(){
        btnCart.click();
    }

    public String validateMessageProducts(){
        messageYourCarText = wait.until(webDriver -> driver.findElement(By.xpath("//span[@class='title']")));
        return messageYourCarText.getText();
    }


}
