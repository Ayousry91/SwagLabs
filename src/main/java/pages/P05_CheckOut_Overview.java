package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_CheckOut_Overview extends PageBase {

    // TODO: constructor to intailize webdriver
    public P05_CheckOut_Overview(WebDriver driver) {
        super(driver);
    }

    // TODO: define locators using By
    private final By ITEMTOTALPRICE = By.xpath("//div[@class='summary_subtotal_label']");

    private final By FINISHBUTTON = By.xpath("//a[@class='btn_action cart_button']");


    // TODO: public action methods
    public String getITEMTOTALPRICE() {
        String checkoutitemtotalprice = driver.findElement(this.ITEMTOTALPRICE).getText();
        System.out.println("checkoutpage overview items total price without tax : " + checkoutitemtotalprice);
        return checkoutitemtotalprice;
    }

    public P05_CheckOut_Overview clickONFINISHBUTTON() {
        driver.findElement(this.FINISHBUTTON).click();
        return this;
    }


}
