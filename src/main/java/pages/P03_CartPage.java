package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_CartPage extends PageBase {

    // TODO: constructor to intailize webdriver
    public P03_CartPage(WebDriver driver) {
        super(driver);
    }

    // TODO: define locators using By
    private final By CHECKOUTBUTTON = By.linkText("CHECKOUT");


    // TODO: public action methods
    public P03_CartPage clickONCHECKOUTBUTTON() {
        driver.findElement(this.CHECKOUTBUTTON).click();
        return this;
    }


}
