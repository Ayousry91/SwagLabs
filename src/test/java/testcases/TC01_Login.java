package testcases;

import org.testng.annotations.Test;
import pages.P01_LoginPage;

import static drivers.DriverHolder.getDriver;

public class TC01_Login extends TestBase {

    @Test(priority = 1, description = "Login to SwagLabs With Valid Credientials")
    public void loginToSwagLabsWithValidCredientials_P() throws InterruptedException {
        new P01_LoginPage(getDriver())
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
        Thread.sleep(4000);
    }

}
