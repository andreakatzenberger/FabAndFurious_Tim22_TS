package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PassengerMapPage {
    private WebDriver driver;
    private static String PAGE_URL = "http://localhost:4200/map";

    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement header;

    @FindBy(how = How.ID, using = "logoutBtn")
    private WebElement logoutButton;

    @FindBy(id = "find_route")
    private WebElement heading; //dodati id iks

    @FindBy(id ="calculate")
    private WebElement calculateBtn; //dodati id iks

    @FindBy(id ="confirm")
    private WebElement confirmBtn; //dodati id iks

    @FindBy(id ="from")
    private WebElement fromBtn; //dodati id iks

    @FindBy(id ="to")
    private WebElement toBtn; //dodati id iks

    @FindBy(css = "div[data-role='custom_select_options']") //?
    private WebElement types;

    @FindBy(id ="now")
    private WebElement nowBtn;

    public PassengerMapPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.textToBePresentInElement(header, "Order new ride"));
    }

    public void logout(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    private void setDeparture(String from){
        fromBtn.clear();
        fromBtn.sendKeys(from);
    }
    private void setDestination(String to){
        toBtn.clear();
        toBtn.sendKeys(to);
    }

    private void clickOnCalculateButton(){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(calculateBtn)).click();
    }

    private void clickOnConfirmButton(){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
    }

    private void clickOnNowButton(){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(nowBtn)).click();
    }

    private void chooseVehicleType(String type) {
        String locator = String.format(".//div[@data-role='custom_select_option_title'][text() = '%s']", type);
        types.findElement(By.xpath(locator)).click();
    }

    public void createNewRide(String from, String to, String type){
        setDeparture(from);
        setDestination(to);
        chooseVehicleType(type);
        clickOnCalculateButton();
        clickOnNowButton();
        clickOnConfirmButton();
    }
}
