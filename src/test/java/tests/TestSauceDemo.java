package tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.LoggerUtil;
import utils.Retry;

public class TestSauceDemo extends PageFactoryInitializer {

    @Test(alwaysRun = true)
    public void urlTest(){
        LoggerUtil.logInfo("urlTest");
       String pageTitle =  loginPage().pageTitle();
        LoggerUtil.logInfo("Verifying the result");

        Assert.assertEquals(pageTitle,"Swag Labs");
        // Set captured logs in the ITestResult attribute
        result.setAttribute("capturedLogs", LoggerUtil.getThreadLocalLogs());

    }
    @Test(priority = 1,dependsOnMethods = "urlTest", retryAnalyzer = Retry.class)
    public void lowtohigh(){
        LoggerUtil.logInfo("lowtohigh");
        loginPage().enterUsername("standard_user");
        loginPage().enterPassword("secret_sauce");
        loginPage().clickLogin();
        homePage().SelectFilter("Price (low to high)");
        Assert.assertTrue(1 == 2);
    }

    @Test(priority = 2,dependsOnMethods = "urlTest")
    public void hightolow(){
        loginPage().enterUsername("standard_user");
        loginPage().enterPassword("secret_sauce");
        loginPage().clickLogin();
        homePage().SelectFilter("Price (high to low)");
      //  Assert.assertTrue(1 == 2);
    }
//
//    @Test(priority = 99,dependsOnMethods = "loginScreen")
//    public void logout(){
//
//
//     //   hp.LogOut();
//
//    }


}
