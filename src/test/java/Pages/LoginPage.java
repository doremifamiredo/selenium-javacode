package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseSeleniumPage {

    @FindBy(xpath = "//*[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//*[@id='checkboxPrivate']")
    private WebElement checkbox;

    private final String adminLogin = "somov_oleg";
    private final String adminPassword = "DY;nwmkgzpnNx9n";

    public LoginPage()  {
        PageFactory.initElements(driver, this);
    }


    public Dashboard authorizationAdmin() {
        driver.get("https://aqa-admin.javacode.ru/login");
        username.sendKeys(adminLogin);
        password.sendKeys(adminPassword, Keys.ENTER);
        return new Dashboard();
    }

    public Account autorizationAccount() {
        driver.get("https://aqa.javacode.ru/login");
        username.sendKeys(adminLogin);
        password.sendKeys(adminPassword, Keys.ENTER);
        return new Account();
    }
}
