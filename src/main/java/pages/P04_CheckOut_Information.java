package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_CheckOut_Information extends PageBase {

    // TODO: constructor to intailize webdriver
    public P04_CheckOut_Information(WebDriver driver) {
        super(driver);
    }

    // TODO: define locators using By
    private final By FIRSTNAME_TEXT = By.id("first-name");
    private final By LASTNAME_TEXT = By.id("last-name");
    private final By ZIPCODE_BUTTON = By.id("postal-code");
    private final By CONTINUEBUTTON = By.xpath("//input[@type='submit']");


    // TODO: public action methods
    public P04_CheckOut_Information clickONFIRSTNAME_TEXT_AND_ENTERRANDOMDATA(String firstname) {
        driver.findElement(this.FIRSTNAME_TEXT).sendKeys(firstname);
        return this;
    }

    public P04_CheckOut_Information clickONLASTNAME_TEXT_AND_ENTERRANDOMDATA(String lastname) {
        driver.findElement(this.LASTNAME_TEXT).sendKeys(lastname);
        return this;
    }

    public P04_CheckOut_Information clickONZIPCODEBUTTON_AND_ENTERRANDOMDATA(String zipcode) {
        driver.findElement(this.ZIPCODE_BUTTON).sendKeys(zipcode);
        return this;
    }

    public P04_CheckOut_Information clickONCONTINUEBUTTON() {
        driver.findElement(this.CONTINUEBUTTON).click();
        return this;
    }


}
