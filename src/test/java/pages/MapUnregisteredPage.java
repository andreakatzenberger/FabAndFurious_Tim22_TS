package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MapUnregisteredPage {
    private WebDriver driver;
    private static String PAGE_URL = "http://localhost:4200/";

    @FindBy(how = How.ID, using = "loginBtn")
    private WebElement loginButton;

    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement header;

    public MapUnregisteredPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.textToBePresentInElement(header, "Find route"));
    }

    public void clickOnLoginButton(){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}
