package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Dashboard extends BaseSeleniumPage {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @FindBy(xpath = "//*[@class='menuProfile']")
    public WebElement profile;

    @FindBy(xpath = "//tr/td/span[1]")
    public WebElement firstBox;
    @FindBy(xpath = "//thead/following::div[1]")
    public WebElement firstDiv;

    @FindBy(xpath = "//tbody/tr/td[2]")
    public WebElement login;
    @FindBy(xpath = "//tbody/tr/td[3]")
    public WebElement name;
    @FindBy(xpath = "//tbody/tr/td[4]")
    public WebElement lastName;

    @FindBy(xpath = "//div[@class='menuProfile']")
    private WebElement nameProfile;

    public Dashboard()  {
        PageFactory.initElements(driver, this);
    }

    public CreationPage menu(String link) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.linkText(link)).click();
        return new CreationPage();
    }

    public String getNameUserProfile() {
        return nameProfile.getText();
    }

}
