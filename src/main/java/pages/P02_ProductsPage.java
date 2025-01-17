package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class P02_ProductsPage extends PageBase {

    public P02_ProductsPage(WebDriver driver) {
        super(driver);
    }

    private final By PRODUCT_BUTTON = By.xpath("(//button[text()='ADD TO CART'])[1]");

    List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[text()='ADD TO CART']"));
    List<WebElement> priceElements = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

    public final By CART_BUTTON = By.id("shopping_cart_container");

    public P02_ProductsPage clickFirstProduct() {
        this.driver.findElement(PRODUCT_BUTTON).click();
        return this;
    }


    List<Double> listPrices = new ArrayList<>();
    public double totalPrice;


    public P02_ProductsPage addRandomProductsAndGetPrices(int numberOfProductsToAdd) throws InterruptedException {
        System.out.println("Number of products to add: " + numberOfProductsToAdd);

        Random random = new Random();


        while (listPrices.size() < numberOfProductsToAdd && !addToCartButtons.isEmpty()) {
            int randomIndex = random.nextInt(addToCartButtons.size());
            WebElement addToCartButton = addToCartButtons.get(randomIndex);
            WebElement priceElement = priceElements.get(randomIndex);

            addToCartButton.click();
            String priceText = priceElement.getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            // Add the price to the list and accumulate the total price
            listPrices.add(price);
            totalPrice += price;
            System.out.println("Selected product index: " + randomIndex + ", Price: $" + priceText);

            // Remove the clicked button and its corresponding price from the lists
            addToCartButtons.remove(randomIndex);
            priceElements.remove(randomIndex);
            Thread.sleep(2000);
        }
        System.out.println("Total price of selected products: $" + totalPrice);
        Thread.sleep(3000);
        return this;
    }

    public String getTotalPrice() {
        return String.format("%.2f", totalPrice);
    }


    public P02_ProductsPage clickONCART_BUTTON() {
        driver.findElement(this.CART_BUTTON).click();
        return this;
    }


}
