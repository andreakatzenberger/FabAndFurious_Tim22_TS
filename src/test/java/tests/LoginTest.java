package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;


public class LoginTest extends BaseTest {

    //passenger
    static final String EMAIL_PASSENGER = "marko.markovic@gmail.com";
    static final String PASSWORD_PASSENGER = "marko123";

    //driver
    static final String EMAIL_DRIVER = "andrea.katzenberger@gmail.com";
    static final String PASSWORD_DRIVER = "andrea123";

    //admin
    static final String EMAIL_ADMIN = "admin@gmail.com";
    static final String PASSWORD_ADMIN = "fica123";

    //wrong credentials
    static final String EMAIL_WRONG = "blabla@gmail.com";
    static final String PASSWORD_WRONG = "blabla";

    @Test
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

        //Create object of PassengerPage class
        PassengerMapPage passengerMapPage = new PassengerMapPage(driver);

        //Check if passenger is logged in
        Assert.assertTrue(passengerMapPage.isPageOpened());

        //Logout
        passengerMapPage.logout();

    }

    @Test
    public void loginAsDriver(){
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
        DriverPage driverPage = new DriverPage(driver);

        //Check if driver is logged in
        Assert.assertTrue(driverPage.isPageOpened());

        //Logout
        driverPage.logout();

    }

    @Test
    public void loginAsAdmin(){
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
        loginPage.login(EMAIL_ADMIN, PASSWORD_ADMIN);

        //Check if alert is for successful login
        Assert.assertEquals((new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent()).getText(), "Successful login!");

        //Click 'OK' on alert
        driver.switchTo().alert().accept();

        //Create object of AdminPage class
        AdminPage adminPage = new AdminPage(driver);

        //Check if admin is logged in
        Assert.assertTrue(adminPage.isPageOpened());

        //Logout
        adminPage.logout();

    }

    @Test
    public void loginAsNonexistingUser(){
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
        loginPage.login(EMAIL_WRONG, PASSWORD_WRONG);

        //Check if alert for wrong credentials
        Assert.assertEquals((new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent()).getText(), "Wrong username or password!");

        //Click 'OK' on alert
        driver.switchTo().alert().accept();

    }
}
