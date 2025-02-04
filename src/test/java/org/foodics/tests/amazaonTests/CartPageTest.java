package org.foodics.tests.amazaonTests;

import org.foodics.base.BaseTest;
import org.foodics.pages.amazonPages.CartPage;
import org.foodics.pages.amazonPages.VideoGamesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.foodics.pages.amazonPages.VideoGamesPage.totalCount;

public class CartPageTest extends BaseTest {

    private static double actualSubTotalAmount;

    private static int actualSubTotalCount;
    private CartPage cartPage;
    private VideoGamesPage videoGamesPage;

    // Constructor to initialize page objects
    public CartPageTest() {
        VideoGamesPage videoGamesPage = new VideoGamesPage(driver);
        cartPage = new CartPage(driver);

    }

    @Test
    public void validateCartAmount() {
        // Get the subtotal from CartPage (actual value)
        actualSubTotalAmount =cartPage.getSubTotalAmount();

        // Get the expected subtotal amount from VideoGamesPage

        double expectedSubTotalAmount = videoGamesPage.totalAmount;


        // Assertion to verify if Subtotal Amount is as selected before moving to cart page
        Assert.assertEquals(actualSubTotalAmount, expectedSubTotalAmount, "Subtotal amount mismatch!");
    }

    @Test

    public void validateCartCount() {
        // Get the subtotal from CartPage (actual value)
        actualSubTotalAmount =cartPage.getSubTotalCount();

        // Get the expected subtotal amount from VideoGamesPage

        int expectedSubTotalCount = totalCount;


        // Assertion to verify if Subtotal Amount is as selected before moving to cart page
        Assert.assertEquals(actualSubTotalCount, expectedSubTotalCount, "Subtotal amount mismatch!");
    }


}


