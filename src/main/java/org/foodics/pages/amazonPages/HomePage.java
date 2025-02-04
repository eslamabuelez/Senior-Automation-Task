package org.foodics.pages.amazonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    // Locators
    private By allMenuButton = By.id("nav-hamburger-menu");
    private By seeAll = By.xpath("//div[text()='See all']");
    private By videoGames = By.xpath("//div[text()='Video Games']");
    private By allVideoGames = By.xpath("(//li//a[@class='hmenu-item' and contains(text(), 'All Video Games')])[2]");
    //private String allVideoGamesURl = "https://www.amazon.eg/-/en/gp/browse.html?node=18022560031&ref_=nav_em_vg_all_0_2_16_2";
    private String currentURL;
    private  WebDriver driver;
    private WebDriverWait wait;





    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void navigateAmazon(String amazonURL) {
        driver.get(amazonURL);
        System.out.println("Navigatd to Amazon ");
    }



    // Click the All Menu button
    public void openAllMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(allMenuButton)).click();
        System.out.println("All Menu is clicked");
    }

    // Scroll to the See All element using Actions
//    public void navigateToHomePage()  {
//        driver.get("https://www.amazon.eg/?language=en_AE&ref_=nav_ya_signin");
//        System.out.println("Navigated to home page");
//    }
    public void scrollToSeeAll() throws InterruptedException {
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(seeAll)).perform();
        System.out.println("Scrolled to See all");
    }

    // Click the See All element
    public void clickSeeAll() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(seeAll)).click();
        System.out.println("See all is clicked");
    }

    // Wait until Video Games element is visible
    public void waitVideoGamesVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(videoGames));
    }

    // Scroll to the Video Games element using Actions
    public void scrollToVideoGames() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(videoGames)).perform();
        System.out.println("Scrolled to Video Games");
    }

    // Click the Video Games element
    public void clickVideoGames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(videoGames)).click();
        System.out.println("Video Games is clicked");
    }

    // Click All Video Games on the Left Navigation
    public void clickAllVideoGames() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(allVideoGames));
        driver.findElement(allVideoGames).click();
        System.out.println("All Video Games is clicked");
    }
    public String getCurrentURL() {
        currentURL = driver.getCurrentUrl();
        // Use .equals() to compare the URL strings
        return currentURL;
    }
}