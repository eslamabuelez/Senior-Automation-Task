package org.foodics.pages.amazonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By subTotalCount = By.xpath("(//span[@class='a-size-medium sc-number-of-items'])[2]");
    private By subTotalAmount = By.xpath("(//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap'])[2]");

    // Constructor to initialize WebDriver
    public CartPage(WebDriver driver) {
        this.driver = driver;
         // Import at the top

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public int getSubTotalCount() {
        String cartSubTotalCountText = driver.findElement(subTotalCount).getText();
        int cartSubTotalCount = Integer.parseInt(cartSubTotalCountText);
        System.out.println("SubTotal Count is: " + cartSubTotalCount);
        return cartSubTotalCount;
    }

    public double getSubTotalAmount() {
        String cartSubTotalAmountText = driver.findElement(subTotalAmount).getText().replace(",", "");
        double cartSubTotalAmount = Double.parseDouble(cartSubTotalAmountText);
        System.out.println("SubTotal Amount is: " + cartSubTotalAmount);
        return cartSubTotalAmount;
    }
}
