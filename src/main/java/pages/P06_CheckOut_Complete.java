package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06_CheckOut_Complete extends PageBase {

    // TODO: constructor to intailize webdriver
    public P06_CheckOut_Complete(WebDriver driver) {
        super(driver);
    }

    // TODO: define locators using By
    private final By HEADERMESSAGE = By.xpath("//h2[@class='complete-header']");


    // TODO: public action methods
    public String getHEADERMESSAGE() {
        String hEADERMESSAGE = driver.findElement(this.HEADERMESSAGE).getText();
        return hEADERMESSAGE;
    }


}
