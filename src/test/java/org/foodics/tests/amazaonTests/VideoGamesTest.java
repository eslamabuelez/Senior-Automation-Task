package org.foodics.tests.amazaonTests;

import org.foodics.base.BaseTest;
import org.foodics.pages.amazonPages.VideoGamesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VideoGamesTest extends BaseTest {
    private static String currentURL;
    private static String cartURL = "https://www.amazon.eg/-/en/gp/cart/view.html?ref_=nav_cart";
    private VideoGamesPage videoGamesPage;
  //ChromeDriver driver = new ChromeDriver();


//    @BeforeClass
//    public void setUpTest() {
//        // Initialize the HomePage instance with the WebDriver
//
//        homePage = new HomePage(driver);
//        driver = new ChromeDriver();
//    }


    @BeforeMethod
    public void homePageSetUp(){
        videoGamesPage = new VideoGamesPage(driver);
    }


    @Test
    public void testAddProductsBelow15k() {
        // Navigate to the Video Games page
        driver.get("https://www.amazon.eg/-/en/gp/browse.html?node=18022560031&ref_=nav_em_vg_all_0_2_16_2");

        // Initialize the VideoGames page
        VideoGamesPage videoGamesPage = new VideoGamesPage(driver);

       videoGamesPage.waitPageLoad();
        videoGamesPage.scrollToFreeShipping();
        // Apply filters and sort
        videoGamesPage.clickFreeShipping();
        System.out.println("free Shipping is selected");
        videoGamesPage.clickNew();
        System.out.println("New is clicked");
        videoGamesPage.scrollToSortBy();
        //videoGamesPage.waitSortByVisible();
        videoGamesPage.clickSortBy();

        videoGamesPage.clickPriceHighToLow();

        // Add products below 15k EGP to the cart
        videoGamesPage.addProductsBelow15k();


    //Click cart Button
        videoGamesPage.clickCartButton();



        videoGamesPage.getCurrentURL();
        Assert.assertEquals(currentURL,cartURL, "URL does not match!");
    }

}
