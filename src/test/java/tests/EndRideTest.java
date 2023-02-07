package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CurrentRideDriverPage;
import pages.LoginPage;
import pages.MapUnregisteredPage;

public class EndRideTest extends BaseTest{

    //driver credentials for login
    static final String EMAIL_DRIVER = "andrea.katzenberger@gmail.com";
    static final String PASSWORD_DRIVER = "andrea123";

    @Test
    public void endCurrentRide(){
        //Create object of MapUnregistered class
        MapUnregisteredPage mapUnregisteredPage = new MapUnregisteredPage(driver);

        //Check if page is opened
        Assert.assertTrue(mapUnregisteredPage.isPageOpened());

        //Redirect to login
        mapUnregisteredPage.clickOnLoginButton();

        //Create object of LoginPage class
        LoginPage loginPage = new LoginPage(driver);

        //Check if page is opened
        Assert.assertTrue(loginPage.isPageOpened());

        //Fill up data
        loginPage.login(EMAIL_DRIVER, PASSWORD_DRIVER);

        //Check if alert is successful login
        Assert.assertEquals((new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent()).getText(), "Successful login!");

        //Click 'OK' on alert
        driver.switchTo().alert().accept();

        //Create object of DriverPage class
        CurrentRideDriverPage currentRideDriverPage = new CurrentRideDriverPage(driver);

        //Check if driver is logged in
        Assert.assertTrue(currentRideDriverPage.isPageOpened());

        //End ride
        currentRideDriverPage.clickOnEndRideButton();

        //Check if alert is successful login
        Assert.assertEquals((new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent()).getText(), "Ended ride successfully!");

        //Click 'OK' on alert
        driver.switchTo().alert().accept();
    }

}
