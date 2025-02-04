package org.foodics.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    protected ConfigReader config;

    // Variables to hold the phone and password values
    protected String amazonPhone;
    protected String amazonPassword;

    @BeforeSuite
    public void setUp() {
        // Initialize ConfigReader to load configuration properties
        config = new ConfigReader();

        // Set the path to the ChromeDriver executable (ensure this path is correct and minimized)
        String chromeDriverPath = config.getProperty("chrome.driver");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // Set ChromeOptions to use headless mode and other optimizations
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // Runs Chrome in headless mode
        options.addArguments("--disable-gpu"); // Disables GPU acceleration for headless mode
        options.addArguments("--no-sandbox"); // Bypass OS security model for headless mode
        options.addArguments("--disable-dev-shm-usage");// Resolves shared memory issues
        options.addArguments("--guest");// enter as guest to avoid passKey
        // Initialize ChromeDriver instance with options
        if (driver == null) {
            driver = new ChromeDriver(options);
            // Maximize the browser window (optional if headless)
            driver.manage().window().maximize();
        }

        // Set implicit wait to handle element synchronization globally
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//        // Navigate to the Amazon URL specified in the config file
//        String amazonURL = config.getProperty("amazon.url");
//        driver.get(amazonURL);

        // Set the values of amazonPhone and amazonPassword variables
        amazonPhone = getAmazonPhone();
        amazonPassword = getAmazonPassword();
    }

    // Method to get the Amazon phone number from the config file
    public String getAmazonPhone() {
        return config.getProperty("amazon.phone");
    }

    // Method to get the Amazon password from the config file
    public String getAmazonPassword() {
        return config.getProperty("amazon.password");
    }

    // Method to wait for page load to complete
    public void waitPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    // Optionally, you can define a tearDown method to quit the driver after tests
    // @AfterClass
    // public void tearDown() {
    //     if (driver != null) {
    //         driver.quit();
    //     }
    // }
}
