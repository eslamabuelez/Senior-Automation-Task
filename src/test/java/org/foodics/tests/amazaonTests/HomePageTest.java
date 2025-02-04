package org.foodics.tests.amazaonTests;

import org.foodics.base.BaseTest;
import org.foodics.pages.amazonPages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
private String expectedURL;
    HomePage homePage;
    @BeforeClass
    public void homePageSetUp(){
        homePage = new HomePage(driver); // Directly use the driver initialized in BaseTest
       // this.config = new ConfigReader();
    }



    @Test
    public void homPageNavigation()  {
//        String amazonURL = config.getProperty("amazon.url");
//        homePage.navigateAmazon(amazonURL);

        homePage.openAllMenu();

        try {
            homePage.scrollToSeeAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        homePage.clickSeeAll();
        homePage.scrollToVideoGames();
        homePage.clickVideoGames();
        try {
            homePage.clickAllVideoGames();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
   public void validateUserIsdirectedToAllVideoGames(String currentURL, String ExpectedURL) {
   currentURL =homePage.getCurrentURL();
        expectedURL = "https://www.amazon.eg/-/en/gp/browse.html?node=18022560031&ref_=nav_em_vg_all_0_2_16_2";
        assert currentURL.equals(expectedURL) : "URL did not match. Expected: " + expectedURL + ", but got: " + currentURL;

    }
}
