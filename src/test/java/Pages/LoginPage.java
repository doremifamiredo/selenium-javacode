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

    public LoginPage()  {
        driver.get("https://aqa-admin.javacode.ru/login");
        PageFactory.initElements(driver, this);
    }


    public Dashboard authorization(String login, String pass) {
        username.sendKeys(login);
        password.sendKeys(pass, Keys.ENTER);
        return new Dashboard();
    }
}
