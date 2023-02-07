package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage {
    private WebDriver driver;
    private static String PAGE_URL = "http://localhost:4200/admin-profile";

    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement header;

    @FindBy(how = How.ID, using = "logoutBtn")
    private WebElement logoutButton;

    public AdminPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.textToBePresentInElement(header, "My profile"));
    }

    public void logout(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}
