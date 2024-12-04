import Data.Data;
import Pages.Dashboard;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static Data.Data.faker;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class JavaCodeAdmin {

    @org.junit.jupiter.api.Test
    @DisplayName("1. Авторизация в портале")
    void authorizationOnThePortal() {
        LoginPage loginPage = new LoginPage(driver);
        Data.User authInfo = Data.getValidUser();
        Dashboard dashboard = loginPage.authorization(authInfo);
        assertAll(() -> assertEquals(authInfo.name, dashboard.profile.getText()),
                () -> assertEquals("Панель администратора", driver.getTitle()));
    }

    @Test
    @DisplayName("2. Добавление нового интервью")
    void addingInterview() {
        Data.User user = Data.getValidUser();
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys(user.login);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(user.password);
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Интервью")));

//        List<WebElement> root = driver.findElements(By.xpath("//div[@class='headerCont']//*/a"));
 //       root.get(2).click();
        link.click();
       // Dashboard dashboard = new LoginPage(driver).authorization();
        String topic = faker.word().noun();
    //    driver.findElement(By.xpath("//div[@class='headerCont']/*//a[@href='/interviews']")).click();
       // dashboard.addingInterview(topic);
    }

    @Test
    void newQuestion() throws InterruptedException {
        Data.User user = Data.getValidUser();
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys(user.login);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(user.password);
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Вопросы')]")).click();
        Thread.sleep(1000);
    }

    @Test
    void newQuiz() throws InterruptedException {
        Data.User user = Data.getValidUser();
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys(user.login);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(user.password);
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Квизы')]")).click();
        Thread.sleep(1000);
    }

    @Test
    void newModule() throws InterruptedException {
        Data.User user = Data.getValidUser();
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys(user.login);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(user.password);
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Модули')]")).click();
        Thread.sleep(1000);
    }

    @Test
    void newCourse() throws InterruptedException {
        Data.User user = Data.getValidUser();
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys(user.login);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(user.password);
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Курсы')]")).click();
        Thread.sleep(1000);
    }

    @Test
    void newUser() throws InterruptedException {
        Data.User user = Data.getValidUser();
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys(user.login);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(user.password);
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Пользователи')]")).click();
        Thread.sleep(1000);
    }

    @Test
    void newExam() throws InterruptedException {
        Data.User user = Data.getValidUser();
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys(user.login);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(user.password);
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Экзамены')]")).click();
        Thread.sleep(1000);
    }

    public static WebDriver driver;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void openSite() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        //   options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://aqa-admin.javacode.ru/login");
        driver.manage().window().maximize();
    }

    @AfterEach
    void closing() {
        driver.close();
    }

    @AfterAll
    static void teardown() {
        driver.quit();
    }
}
