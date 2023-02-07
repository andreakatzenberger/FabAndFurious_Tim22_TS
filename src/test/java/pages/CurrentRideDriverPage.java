package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CurrentRideDriverPage {
    private WebDriver driver;
    private static String PAGE_URL = "http://localhost:4200/current-drive-driver";

    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement header;

    @FindBy(how = How.ID, using = "logoutBtn")
    private WebElement logoutButton;

    @FindBy(how = How.ID, using = "endBtn")
    private WebElement endButton;

    public CurrentRideDriverPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.textToBePresentInElement(header, "Current ride info"));
    }

    public void logout(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public void clickOnEndRideButton(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(endButton)).click();
    }
}
