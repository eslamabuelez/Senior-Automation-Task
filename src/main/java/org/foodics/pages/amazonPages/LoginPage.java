package org.foodics.pages.amazonPages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {




    private By language = By.xpath("//a[@id='icp-nav-flyout']");
    private By englishOption = By.xpath("(//i[@class='a-icon a-icon-radio'])[2]");
    private By saveLanguage = By.xpath("//span[@id='icp-save-button']");
    private By accounts = By.xpath("//a[@aria-controls=\"nav-flyout-accountList\"]");
    private By phoneNumberField = By.id("ap_email");
    private By accountName = By.id("nav-link-accountList-nav-line-1");

    private By continueButton = By.xpath("//input[@id='continue']");
   // private passwordField;
   private By passwordField = By.id("ap_password");
    private By signInButton = By.id("signInSubmit");

    private static String expectedUsername = "Automation";
    private   WebDriver driver;
    private  WebDriverWait wait;
    public LoginPage(WebDriver driver) {
        this.driver = driver; // Use the instance variable from BaseTest
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void navigateAmazon(String amazonURL) {
        driver.get(amazonURL);
        System.out.println("Navigatd to Amazon ");
    }
    public void clickLanguage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(language)).click();
        System.out.println("Language is clicked");
    }

    public void selectEnglish() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(englishOption)).click();
        System.out.println("English Language is selected");
    }

    public void saveLanguage() {
        wait.until(ExpectedConditions.elementToBeClickable(saveLanguage)).click();
        System.out.println("Language selection is saved");
    }

    public void refreshPage() {
        driver.navigate().refresh();
        System.out.println("Page refreshed successfully");
    }

    public void waitPageLoad() {
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    public void clickAccounts() {
        wait.until(ExpectedConditions.elementToBeClickable(accounts)).click();
        System.out.println("Language selection is saved");
    }
    public void setPhoneNumber(String amazonPhone) {
        try {
            // Try the primary locator first (assuming phoneNumberField is already defined)
            driver.findElement(phoneNumberField).sendKeys(amazonPhone);
            System.out.println("Phone Number is Typed: " + amazonPhone);
        } catch (NoSuchElementException e) {
            // If the primary locator is not found, try the alternative locator
            System.err.println("Primary locator not found. Trying alternative locator...");
            driver.findElement(By.xpath("//input[@type='email']")).sendKeys(amazonPhone);
            System.out.println("Phone Number is Typed (using alternative locator): " + amazonPhone);
        } catch (ElementNotInteractableException e) {
            // If the element is not interactable
            System.err.println("Error: The phone number field is not interactable.");
            e.printStackTrace();
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.err.println("An unexpected error occurred while entering the phone number.");
            e.printStackTrace();
        }
    }





    public void clickContinue() {
        try {
            // Try the primary locator first
            wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
            System.out.println("Continue button is clicked");
        } catch (Exception e) {
            System.err.println("Primary locator not found or not clickable. Trying alternative locator...");

            try {
                // If the primary locator fails, try the alternative locator
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']"))).click();
                System.out.println("Continue button is clicked using alternative locator");
            } catch (Exception e2) {
                // If the alternative locator fails as well
                System.err.println("Error: Neither primary nor alternative locator worked.");
                e2.printStackTrace();
            }
        }
    }





    public void dismissPasskeyPopup() {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(org.openqa.selenium.Keys.ESCAPE).perform();
            System.out.println("Passkey popup detected and dismissed.");
        } catch (Exception e) {
            System.out.println("No passkey popup detected. Continuing...");
        }
    }


    // Helper function to check if an element exists
    private static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }



    public void setPassword(String amazonPassword) {


        wait.until(ExpectedConditions.elementToBeClickable(passwordField));

        driver.findElement(passwordField).sendKeys(amazonPassword);
        System.out.println("Phone Number is Typed "+amazonPassword);
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
        System.out.println("Sign in button is clicked");
    }
    public void validateAccountName() {
        // Get the actual account name
        String actualAccountName = wait.until(ExpectedConditions.visibilityOfElementLocated(accountName)).getText();

        // Remove "Hello, " from the actual account name
        String cleanedAccountName = actualAccountName.replace("Hello, ", "").trim();

        // Perform the validation
        assert cleanedAccountName.contains(expectedUsername);

        // Print the expected and actual names
        System.out.println("Expected username is: " + expectedUsername);
        System.out.println("Actual username is: " + cleanedAccountName);
    }


}
