package testcases;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.Random;

import static drivers.DriverHolder.getDriver;

public class TC02_Products extends TestBase {

    @Test(priority = 2, description = "Add Product from product page")
    public void verifyUserCanAddProductSuccessfully_P() throws InterruptedException {
        // TODO: login to app
        new P01_LoginPage(getDriver()).enterUsername(username).enterPassword(password).clickLoginButton();

        // TODO: select product
        new P02_ProductsPage(getDriver()).clickFirstProduct();


        // TODO: sleep 3 sec
        Thread.sleep(3000);
    }

    @Test(priority = 1, description = "Add Random Products from product page")
    public void verifyUserCanAddRandomProductSuccessfully_P() throws InterruptedException {
        // TODO: login to app
        Thread.sleep(2000);
        new P01_LoginPage(getDriver()).enterUsername(username).enterPassword(password).clickLoginButton();

        // TODO: Select and Add Random Products to the Shopping Cart
        Random random = new Random();
        int randomx = random.nextInt(6) + 1;
        String totalPrice = new P02_ProductsPage(getDriver()).addRandomProductsAndGetPrices(randomx).getTotalPrice();

        // TODO: Click on Cart button to redirect to the cart  page
        new P02_ProductsPage(getDriver()).clickONCART_BUTTON();

        //TODO: ON the Cart page Click on CHECKOUT BUTTON
        Thread.sleep(2000);
        new P03_CartPage(getDriver()).clickONCHECKOUTBUTTON();

        //TODO: ON checkout-step-one, enter random data in (firstname, lastname, zipcode) fields and click on Continue button
        Thread.sleep(2000);
        Faker faker = new Faker();
        new P04_CheckOut_Information(getDriver()).clickONFIRSTNAME_TEXT_AND_ENTERRANDOMDATA(faker.name().firstName())
                .clickONLASTNAME_TEXT_AND_ENTERRANDOMDATA(faker.name().lastName())
                .clickONZIPCODEBUTTON_AND_ENTERRANDOMDATA(faker.number().digits(5))
                .clickONCONTINUEBUTTON();

        //TODO : on checkout page overview assert on price and click on Finish button
        Thread.sleep(2000);
        Assert.assertTrue(new P05_CheckOut_Overview(getDriver()).getITEMTOTALPRICE().contains(totalPrice));

        new P05_CheckOut_Overview(getDriver()).clickONFINISHBUTTON();

        //TODO: Assert on the header message to verify your success order
        Thread.sleep(2000);
        Assert.assertTrue(new P06_CheckOut_Complete(getDriver()).getHEADERMESSAGE().contains("THANK YOU FOR YOUR ORDER"));


    }


}
