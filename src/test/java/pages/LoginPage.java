package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private static String PAGE_URL = "http://localhost:4200/login";

    @FindBy(how = How.ID, using = "header")
    private WebElement header;

    @FindBy(how = How.ID, using = "email")
    private WebElement emailInput;

    @FindBy(how = How.ID, using = "password")
    private WebElement passwordInput;

    @FindBy(how = How.ID, using = "loginButton")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.textToBePresentInElement(header, "LogIn"));
    }

    private void setEmail(String email){
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    private void setPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void login(String email, String password){
        setEmail(email);
        setPassword(password);
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}
