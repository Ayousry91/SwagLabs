package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage extends PageBase {

    // TODO: constructor to intailize webdriver
    public P01_LoginPage(WebDriver driver) {
        super(driver);
    }

    // TODO: define locators using By
    private final By USERNAME_TEXT = By.id("user-name");
    private final By PASSWORD_TEXT = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");

    // TODO: public action methods
    public P01_LoginPage enterUsername(String username) {
        driver.findElement(this.USERNAME_TEXT).sendKeys(username);
        return this;
    }

    public P01_LoginPage enterPassword(String password) {
        driver.findElement(this.PASSWORD_TEXT).sendKeys(password);
        return this;
    }

    public P02_ProductsPage clickLoginButton() {
        driver.findElement(this.LOGIN_BUTTON).click();
        return new P02_ProductsPage(driver);
    }

}
