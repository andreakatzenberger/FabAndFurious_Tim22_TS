package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class PassengerMapPage {
    private WebDriver driver;
    private static String PAGE_URL = "http://localhost:4200/map";

    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement header;

    @FindBy(how = How.ID, using = "logoutBtn")
    private WebElement logoutButton;

//    @FindBy(id = "find_route")
//    private WebElement heading; //dodati id iks

    @FindBy(how = How.ID, using = "from")
    private WebElement fromInput;

    @FindBy(how = How.ID, using = "to")
    private WebElement toInput;

    @FindBy(how = How.ID, using = "showRoute")
    private WebElement calculateBtn;

    @FindBy(how = How.ID, using = "vehicleType")
    private WebElement vehicleType;

//    @FindBy(how = How.ID, using = "now")
    @FindBy(how = How.ID, using = "now")
    private WebElement nowBtn;

    @FindBy(how = How.ID, using = "save-btn")
    private WebElement confirmBtn;

    @FindBy(how = How.ID, using = "pets")
    private WebElement petTransportBtn;

    @FindBy(how = How.ID, using = "pets")
    private WebElement babyTransportBtn;


    public PassengerMapPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);

        PageFactory.initElements(driver, this);
//        JavascriptExecutor executor = (JavascriptExecutor)driver;
//        executor.executeScript("document.body.style.zoom = '0.75'");
    }

    public boolean isPageOpened(){
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.textToBePresentInElement(header, "Find route"));
    }

    public void logout(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    private void setDeparture(String from){
        fromInput.clear();
        fromInput.sendKeys(from);
    }
    private void setDestination(String to){
        toInput.clear();
        toInput.sendKeys(to);
    }

    private void chooseVehicleType(String type) {
        Select select = new Select(vehicleType);
        select.selectByValue(type);
    }

    private void clickOnCalculateButton(){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(calculateBtn)).click();
    }

    private void clickOnNowButton() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
//        (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.visibilityOf(nowBtn));
        Thread.sleep(500);
        driver.findElement(By.id("now")).click();
    }

    private void clickOnConfirmButton(){
        driver.findElement(By.id("save-btn")).click();
    }

    private void clickOnPetTransportButton(){
        driver.findElement(By.id("pets")).click();
    }

    private void clickOnBabyTransportButton(){
        driver.findElement(By.id("babies")).click();
    }

    public void createNewRide(String from, String to, String type) throws InterruptedException {
        setDeparture(from);
        setDestination(to);
        clickOnPetTransportButton();
        clickOnBabyTransportButton();
        chooseVehicleType(type);
        clickOnCalculateButton();
        clickOnNowButton();
        clickOnConfirmButton();
    }

    public void createNewRideNoInput(String from, String to, String type){
        setDeparture(from);
        setDestination(to);
        chooseVehicleType(type);
        clickOnCalculateButton();
    }
}
