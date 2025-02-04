package org.foodics.pages.amazonPages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class VideoGamesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By freeShippingCheckBox = By.xpath("//span[@class='a-size-base a-color-base'][normalize-space()='Free Shipping']");
    private By conditionNew = By.id("p_n_condition-type/28071525031");
    private By sortBy = By.xpath("//span[@class='a-dropdown-label' and text()='Sort by:']");
    private By priceHighToLow = By.id("s-result-sort-select_2");
    private By allProducts = By.cssSelector(".s-result-item");
    private By productPrice = By.cssSelector(".a-price-whole");
    private By addToCartButton = By.xpath("//button[@class='a-button-text' and text()='Add to cart']");
    private By nextPageButton = By.cssSelector(".a-last");

    private  By cartButton = By.id("nav-cart");


    private static String currentURL;
    public static double totalAmount = 0.0;
    public static int totalCount = 0;
    public VideoGamesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void refreshPage() {
        driver.navigate().refresh();
        waitPageLoad();  // Ensure the page has fully loaded after the refresh
    }

    public void waitPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    public void scrollToFreeShipping() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(freeShippingCheckBox));
    }

    public void clickFreeShipping() {
        driver.findElement(freeShippingCheckBox).click();
    }

    public void clickNew() {
        driver.findElement(conditionNew).click();
    }

    public void scrollToSortBy() {
        try {
            // FluentWait to ensure the element is present before scrolling
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);

            WebElement sortByElement = wait.until(driver -> {
                WebElement element = driver.findElement(sortBy);
                return element.isDisplayed() ? element : null;
            });

            // Use Actions class to move to the element
            Actions actions = new Actions(driver);
            actions.moveToElement(sortByElement).perform();

            System.out.println("Scrolled to Sort By");

        } catch (Exception e) {
            System.out.println("Failed to scroll to Sort By: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickSortBy() {
        try {
            // FluentWait to ensure the element is present before clicking
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);

            WebElement sortByElement = wait.until(driver -> {
                WebElement element = driver.findElement(sortBy);
                return element.isDisplayed() && element.isEnabled() ? element : null;
            });

            // Scroll to element using Actions
            Actions actions = new Actions(driver);
            actions.moveToElement(sortByElement).perform();

            // Click on the element
            sortByElement.click();
            System.out.println("Clicked on Sort By");

        } catch (Exception e) {
            System.out.println("Failed to click on Sort By: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickPriceHighToLow() {
        driver.findElement(priceHighToLow).click();
        System.out.println("Video Games are Sorted from High Price to Low Price");
    }

    /**
     * Adds products below 15k EGP to the cart.
     * If no products are found, move to the next page.
     */
    public void addProductsBelow15k() {
        int itemsAddedToCart = 0;  // Counter to track the number of items added to the cart
        double totalAmount = 0.0;  // Variable to keep track of the total amount spent
        boolean productsAdded = false;
        int maxRetries = 3;  // Maximum retry attempts for adding an item to the cart

        do {
            List<WebElement> products = driver.findElements(allProducts);
            boolean foundProductOnPage = false;

            for (WebElement product : products) {
                try {
                    // Extract and parse the product price
                    String priceText = product.findElement(productPrice).getText().replace(",", "");
                    double price = Double.parseDouble(priceText);  // Use double for currency handling

                    // Check if the price is below 15,000
                    if (price < 15000) {
                        foundProductOnPage = true;
                        System.out.println("product price is "+ priceText);
                        boolean itemAdded = false;
                        int retries = 0;

                        while (retries < maxRetries && !itemAdded) {
                            try {
                                // Locate the "Add to Cart" button within the product container using the corrected XPath
                                WebElement addToCart = product.findElement(By.xpath(".//button[@class='a-button-text' and text()='Add to cart']"));

                                // Click the button if it is displayed and enabled
                                if ( addToCart.isEnabled()) {
                                    addToCart.click();
                                    System.out.println("product with price  "+ priceText+ " is added");
                                    itemsAddedToCart++;  // Increment the counter when a product is added to the cart
                                    totalAmount += price;  // Add the product's price to the total amount
                                    itemAdded = true;  // Set flag to true to exit the retry loop
                                }
                            } catch (Exception e) {
                                retries++;  // Increment retry count
                                System.out.println("'Add to Cart' failed for a product. Attempt " + retries + " of " + maxRetries);
                                if (retries >= maxRetries) {
                                    System.out.println("Max retries reached for this product, skipping...");
                                }
                                try {
                                    Thread.sleep(1000);  // Optional delay before retry (e.g., 1 second)
                                } catch (InterruptedException interruptedException) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Add to cart is not persent or the price is +15000  , skipping...");
                }
            }

            // Exit if products have been added
            if (productsAdded) {
                break;
            }

            // If qualifying products are found but none are added, exit the loop
            if (foundProductOnPage) {
                break;
            }

            // If no products are added and no products found, go to the next page
            List<WebElement> nextPageElements = driver.findElements(nextPageButton);
            if (!nextPageElements.isEmpty()) {
                nextPageElements.get(0).click();
                waitPageLoad();  // Ensure the next page is fully loaded before continuing.
            } else {
                break;  // Exit if no next page exists.
            }

        } while (!productsAdded);

        // Output the number of items and total amount added to the cart
        System.out.println("Total items added to cart: " + itemsAddedToCart);
        System.out.println("Total amount spent: " + totalAmount + " EGP");
        System.out.println("Products added to the cart successfully.");
        totalCount= itemsAddedToCart;
    }

    public static int getTotalCount() {
        return totalCount;
    }
    public static double getTotalAmount() {
        return totalAmount;
    }
    public void clickCartButton() {
        try {
            // Try clicking the predefined cartButton first
             wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        } catch (TimeoutException e1) {
            try {
                // Try first alternative locator
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='nav-progressive-attribute']")));
            } catch (TimeoutException e2) {
                try {
                    // Try second alternative locator
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='nav-cart-count-container']")));
                } catch (TimeoutException e3) {
                    throw new NoSuchElementException("Cart button not found using all locators.", e3);
                }
            }
        }
    }
    public String getCurrentURL() {
        currentURL = driver.getCurrentUrl();
        System.out.println("Current URL is : "+ currentURL);
        return currentURL;
    }
}
