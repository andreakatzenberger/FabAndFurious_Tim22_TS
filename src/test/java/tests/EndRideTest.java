package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CurrentRideDriverPage;
import pages.LoginPage;
import pages.MapUnregisteredPage;

public class EndRideTest extends BaseTest{

    //driver credentials for login (with current ride)
    static final String EMAIL1 = "andrea.katzenberger@gmail.com";
    static final String PASSWORD1 = "andrea123";

    //driver credentials for login (without current ride)
    static final String EMAIL2 = "bojana.popov@gmail.com";
    static final String PASSWORD2 = "bojana123";

    //panic reason
    static final String PANIC = "Passengers are being very rude!";

//    @Test
//    public void endCurrentRideExisting() throws InterruptedException {
//        //Login
//        loginAsDriver(EMAIL1, PASSWORD1);
//
//        //Create object of DriverPage class
//        CurrentRideDriverPage currentRideDriverPage = new CurrentRideDriverPage(driver);
//
//        //Check if driver is logged in
//        Assert.assertTrue(currentRideDriverPage.isPageOpened());
//
//        //End ride
//        currentRideDriverPage.clickOnEndRideButton();
//
//        //Check if alert is successful login
//        Assert.assertEquals((new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent()).getText(), "Ended ride successfully!");
//
//        //Click 'OK' on alert
//        driver.switchTo().alert().accept();
//
//        //Logout
//        currentRideDriverPage.logout();
//    }

    @Test
    public void endCurrentRideNonExisting() throws InterruptedException {
        //Login
        loginAsDriver(EMAIL2, PASSWORD2);

        //Create object of DriverPage class
        CurrentRideDriverPage currentRideDriverPage = new CurrentRideDriverPage(driver);

        //Check if driver is logged in
        Assert.assertTrue(currentRideDriverPage.isPageOpened());

        //End ride
        currentRideDriverPage.clickOnEndRideButton();

        //Check if alert is successful login
        Assert.assertEquals((new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent()).getText(), "Can not end ride that does not exist!");

        //Click 'OK' on alert
        driver.switchTo().alert().accept();

        //Logout
        currentRideDriverPage.logout();
    }

//    @Test
//    public void setPanic(){
//        //Login
//        loginAsDriver(EMAIL1, PASSWORD1);
//
//        //Create object of DriverPage class
//        CurrentRideDriverPage currentRideDriverPage = new CurrentRideDriverPage(driver);
//
//        //Check if driver is logged in
//        Assert.assertTrue(currentRideDriverPage.isPageOpened());
//
//        //Set panic
//        currentRideDriverPage.setPanic(PANIC);
//
//        //Check if alert is successful login
//        Assert.assertEquals((new WebDriverWait(driver, 60)).until(ExpectedConditions.alertIsPresent()).getText(), "Panic!");
//
//        //Click 'OK' on alert
//        driver.switchTo().alert().accept();
//
//        //Logout
//        currentRideDriverPage.logout();
//    }

    public void loginAsDriver(String email, String password){
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
        loginPage.login(email, password);

        //Check if alert is successful login
        Assert.assertEquals((new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent()).getText(), "Successful login!");

        //Click 'OK' on alert
        driver.switchTo().alert().accept();
    }

}
