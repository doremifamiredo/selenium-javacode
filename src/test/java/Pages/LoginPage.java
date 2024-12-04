package Pages;

import Data.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(xpath = "//*[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement button;

    @FindBy(xpath = "//*[@id='checkboxPrivate']")
    private WebElement checkbox;

    public WebDriver driver;

    public LoginPage(final WebDriver driver)  {
        PageFactory.initElements(driver, this);
    }

    public Dashboard authorization(Data.User user) {
        username.sendKeys(user.login);
        password.sendKeys(user.password);
        button.click();
        return new Dashboard(driver);
    }

    public Dashboard authorization() {
        Data.User user = Data.getValidUser();
        username.sendKeys(user.login);
        password.sendKeys(user.password);
        button.click();
        return new Dashboard(driver);
    }

}
