package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Dashboard {

    @FindBy(xpath = "//*[@class='menuProfile']")
    public WebElement profile;

    @FindBy(xpath = "//a[contains(text(), 'Курсы')]")
    private WebElement course;
    @FindBy(xpath = "//a[contains(text(), 'Экзамены')]")
    private WebElement exam;
    @FindBy(xpath = "//a[contains(text(), 'Интервью')]")
    private WebElement interview;
    @FindBy(xpath = "//a[contains(text(), 'Модули')]")
    private WebElement module;
    @FindBy(xpath = "//a[contains(text(), 'Вопросы')]")
    private WebElement question;
    @FindBy(xpath = "//a[contains(text(), 'Квизы')]")
    private WebElement quiz;
    @FindBy(xpath = "//a[contains(text(), 'Пользователи')]")
    private WebElement users;
    @FindBy(xpath = "//div[@class='menuProfile']")
    private WebElement nameProfile;

    @FindBy(xpath = "//button[contains(text(), '+ Добавить')]")
    private WebElement addBtn;

    @FindBy(xpath = "//*[@class='ReactModalPortal']")
    private WebElement modalWind;

    @FindBy(xpath = "//*[@placeholder='Название']")
    private WebElement interviewName;

    @FindBy(xpath = "//*[contains(text(), 'Create')]")
    private WebElement createBtn;

    public Dashboard(WebDriver driver)  {
        PageFactory.initElements(driver, this);
    }

    public Dashboard addingInterview(String topic) {
        interview.click();
        addBtn.click();
        interviewName.sendKeys(topic);
        createBtn.click();
        return this;
    }
}
