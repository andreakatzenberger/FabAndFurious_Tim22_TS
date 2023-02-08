package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MapUnregisteredPage;
import pages.PassengerMapPage;

public class OrderNewRideTest extends BaseTest{

    //passenger credentials for login
    static final String EMAIL_PASSENGER = "marko.markovic@gmail.com";
    static final String PASSWORD_PASSENGER = "marko123";

    //new ride valid data
    static final String FROM_VALID = "Bulevar Oslobodjenja 55, Novi Sad";
    static final String TO_VALID = "Bulevar vojvode Stepe 31, Novi Sad";
    static final String TYPE_VALID = "STANDARD";

    //new ride invalid data
    static final String FROM_INVALID = "Temerinska 23, Novi Sad";
    static final String TO_INVALID = "Bulevar oslobodjenja 12, Novi Sad";
    static final String TYPE_INVALID = "VAN";


    @Test(priority = 1)
    public void orderNewRide() throws InterruptedException {
        //Login as passenger
        loginAsPassenger();

        //Create object of PassengerPage class
        PassengerMapPage passengerMapPage = new PassengerMapPage(driver);

        //Check if passenger is logged in
        Assert.assertTrue(passengerMapPage.isPageOpened());

        //Fill in data for new ride
        passengerMapPage.createNewRide("Bulevar oslobodjenja 12", "Temerinska 12", "STANDARD");

        //Check alert
        Assert.assertEquals((new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent()).getText(), "Successfully ordered ride!");

        //Click 'OK' on alert
        driver.switchTo().alert().accept();

//        //Logout
        passengerMapPage.logout();
    }

    @Test(priority = 2)
    public void orderNewRideNoAvailableDrivers() throws InterruptedException {
        //Login as passenger
        loginAsPassenger();

        //Create object of PassengerPage class
        PassengerMapPage passengerMapPage = new PassengerMapPage(driver);

        //Check if passenger is logged in
        Assert.assertTrue(passengerMapPage.isPageOpened());

        //Fill in data for new ride
        passengerMapPage.createNewRide("Bulevar oslobodjenja 12", "Temerinska 12", "VAN");

        //Check alert
        Assert.assertEquals((new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent()).getText(), "Cannot order this ride! There's none available drivers!");

        //Click 'OK' on alert
        driver.switchTo().alert().accept();

        //Logout
        passengerMapPage.logout();
    }

    @Test(priority = 3)
    public void orderNewRideNoInput(){
        //Login as passenger
        loginAsPassenger();

        //Create object of PassengerPage class
        PassengerMapPage passengerMapPage = new PassengerMapPage(driver);

        //Check if passenger is logged in
        Assert.assertTrue(passengerMapPage.isPageOpened());

        //Fill in data for new ride
        passengerMapPage.createNewRideNoInput("", "", "VAN");

        //Check alert
        Assert.assertEquals((new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent()).getText(), "Choose departure and destination by clicking on the map!");

        //Click 'OK' on alert
        driver.switchTo().alert().accept();

        //Logout
        passengerMapPage.logout();
    }

    public void loginAsPassenger(){

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
        loginPage.login(EMAIL_PASSENGER, PASSWORD_PASSENGER);

        //Check if alert is successful login
        Assert.assertEquals((new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent()).getText(), "Successful login!");

        //Click 'OK' on alert
        driver.switchTo().alert().accept();
    }
}
