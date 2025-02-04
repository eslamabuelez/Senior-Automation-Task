package org.foodics.tests.amazaonTests;

import org.foodics.base.BaseTest;
import org.foodics.pages.amazonPages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogInTest extends BaseTest {
    LoginPage loginPage;
    @BeforeMethod
    public void loginSetUp(){
        loginPage = new LoginPage(driver); // Directly use the driver initialized in BaseTest
    }
    @Test
    public void loginUsingConfigFile() {
        String amazonURL = config.getProperty("amazon.url");
        String amazonPhone  =  config.getProperty("amazon.phone");
        String amazonPassword  =  config.getProperty("amazon.password");
        loginPage.navigateAmazon(amazonURL);
        loginPage.waitPageLoad();
        loginPage.clickLanguage();
        loginPage.selectEnglish();
        loginPage.waitPageLoad();
        loginPage.saveLanguage();
        loginPage.waitPageLoad();
        loginPage.refreshPage();
        loginPage.waitPageLoad();
        loginPage.clickAccounts();
        loginPage.waitPageLoad();
        loginPage.setPhoneNumber(amazonPhone);
        loginPage.dismissPasskeyPopup();
        loginPage.clickContinue();
        loginPage.waitPageLoad();
        loginPage.dismissPasskeyPopup();
        loginPage.waitPageLoad();
        loginPage.dismissPasskeyPopup();
        loginPage.setPassword(amazonPassword);
        loginPage.dismissPasskeyPopup();
        loginPage.clickSignIn();
        loginPage.dismissPasskeyPopup();
        loginPage.waitPageLoad();
        loginPage.validateAccountName();

       // loginPage.setPassword();

    }
}