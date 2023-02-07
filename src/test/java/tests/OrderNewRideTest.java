package tests;

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

    //new ride
    static final String FROM = "Bulevar Oslobodjenja 55";
    static final String TO = "Bulevar vojvode Stepe 31";
    static final String TYPE = "Van";

    @Test
    public void orderNewRide(){
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

        //Fill in data for new ride
        passengerMapPage.createNewRide(FROM, TO, TYPE);
    }
}
